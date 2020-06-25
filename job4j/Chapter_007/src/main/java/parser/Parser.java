package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

public class Parser implements Job {

    private static final Logger LOG = LogManager.getLogger(Parser.class.getName());
    private boolean firstTime;

    public Parser() {
    }

    private List<Vacancy> parse(long untilTime) throws IOException {
        List<Vacancy> list = new ArrayList<>();
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        int pages = Integer.parseInt(
                doc.select(
                        "table.sort_options > tbody > tr > td > a"
                ).last().text()
        );
        boolean stop = false;
        for (int page = 1; page <= pages; page++) {
            doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + page).get();
            Elements rows = doc.select("tbody > tr:gt(3)");
            for (Element row : rows) {
                String name = row.child(1).child(0).text();
                if (name.matches("(.*)([Jj])ava(?!\\s{0,3}[Ss]cript)(.*)")) {
                    String time = row.child(5).text();
                    Date date = this.timeFormat(time);
                    if (date.getTime() < untilTime) {
                        stop = true;
                        break;
                    }
                    String link = row.child(1).child(0).attr("href");
                    doc = Jsoup.connect(link).get();
                    String text = doc.select("td.msgBody").get(1).text();
                    list.add(new Vacancy(name, link, text));
                }
            }
            if (stop) {
                break;
            }
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

    private long getTimeFromStartOfYear() {
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        calendar.set(GregorianCalendar.DAY_OF_YEAR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    private Date timeFormat(String time) {
        Calendar date = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        List<String> months = Arrays.asList(
                "янв", "фев", "мар", "апр", "май", "июн",
                "июл", "авг", "сен", "окт", "ноя", "дек"
        );
        if (time.startsWith("с") || time.startsWith("в")) {
            if (time.startsWith("вчера")) {
                date.add(Calendar.DATE, -1);
            }
        } else {
            String[] s = time.split(" ");
            date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(s[0]));
            date.set(Calendar.MONTH, months.indexOf(s[1]));
            date.set(Calendar.YEAR, Integer.parseInt(s[2].substring(0, 2)) + 2000);
        }
        String hourMinute = time.split(", ")[1];
        date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourMinute.substring(0, 2)));
        date.set(Calendar.MINUTE, Integer.parseInt(hourMinute.substring(3)));
        return date.getTime();
    }

    public void load(List<Vacancy> vacancies, Connection connection) {
        try (
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO vacancy(name, text, link) VALUES (?, ?, ?)"
                )
        ) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS "
                            + "vacancy(id serial primary key, "
                            + "name varchar(300), "
                            + "\"text\" text, "
                            + "link varchar(300))"
            );
            if (firstTime) {
                statement.executeUpdate("DELETE FROM vacancy");
            }
            connection.setAutoCommit(false);
            for (Vacancy vacancy : vacancies) {
                preparedStatement.setString(1, vacancy.getName());
                preparedStatement.setString(2, vacancy.getText());
                preparedStatement.setString(3, vacancy.getLink());
                preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                LOG.error(sqlException.getMessage(), sqlException);
            }
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            Class.forName(jobExecutionContext.getJobDetail().getJobDataMap().getString("driver"));
            Connection connection = DriverManager.getConnection(
                    jobExecutionContext.getJobDetail().getJobDataMap().getString("url"),
                    jobExecutionContext.getJobDetail().getJobDataMap().getString("user"),
                    jobExecutionContext.getJobDetail().getJobDataMap().getString("password")
            );
            firstTime = jobExecutionContext.getScheduler().getMetaData().getNumberOfJobsExecuted() == 1;
            long untilTime;
            if (firstTime) {
                untilTime = getTimeFromStartOfYear();
            } else {
                untilTime = jobExecutionContext.getPreviousFireTime().getTime();
            }
            LOG.info("Parsing Started.");
            List<Vacancy> vacancies = this.parse(untilTime);
            LOG.info("Parsing Finished.");
            LOG.info("Loading.");
            this.load(vacancies, connection);
            LOG.info("Loaded " + vacancies.size() + " vacancies");
            connection.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static class Vacancy {
        private final String name;
        private final String link;
        private final String text;

        public Vacancy(final String name, final String link, final String text) {
            this.name = name;
            this.link = link;
            this.text = text;
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
        }

        public String getText() {
            return text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Vacancy vacancy = (Vacancy) o;
            return Objects.equals(name, vacancy.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
