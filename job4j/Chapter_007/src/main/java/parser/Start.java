package parser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Start {
    public static void main(String[] args) throws SchedulerException {
        Properties config = new Properties();
        try (InputStream in = Start.class.getClassLoader().getResourceAsStream(args[0])) {
            config.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("Cron")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(config.getProperty("cron.time"))
                )
                .build();

        JobDetail job = JobBuilder.newJob(Parser.class)
                .usingJobData(
                        "url",
                        config.getProperty("jdbc.url")
                        )
                .usingJobData("user", config.getProperty("jdbc.username"))
                .usingJobData("password", config.getProperty("jdbc.password"))
                .usingJobData("driver", config.getProperty("jdbc.driver"))
                .withIdentity("Parse")
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
