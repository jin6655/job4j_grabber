package ru.job4.quartz;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    public int readTime() {
        Path file = Paths.get(".\\src\\main\\resources\\rabbit.properties");
        Map<String, String> map = new HashMap<>();
        try (BufferedReader read = new BufferedReader(new FileReader(file.toFile()))) {
            while (read.ready()) {
                String[] str = read.readLine().split("=");
                map.put(str[0], str[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.valueOf(map.get("rabbit.interval"));
    }

    public static void main(String[] args) {
        try {
            Scheduler sheduler  = StdSchedulerFactory.getDefaultScheduler();
            sheduler.start();
            JobDetail job = newJob(RAbbit.class).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(new AlertRabbit().readTime())
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            sheduler.scheduleJob(job,trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static class RAbbit implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }

}
