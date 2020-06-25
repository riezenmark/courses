package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzUsage {
    public static void main(String[] args) throws SchedulerException {
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("Cron")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                )
                .build();

        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("HelloJob")
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
