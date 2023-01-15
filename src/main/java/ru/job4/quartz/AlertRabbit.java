package ru.job4.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    private static Map<String, String> map = new HashMap<>();

    public static void readProperties() {
        Path file = Paths.get(".\\src\\main\\resources\\rabbit.properties");
        try (BufferedReader read = new BufferedReader(new FileReader(file.toFile()))) {
            while (read.ready()) {
                String[] str = read.readLine().split("=");
                map.put(str[0], str[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() throws Exception {
        Class.forName(map.get("driver"));
        String url = map.get("url");
        String login = map.get("login");
        String password = map.get("password");
        return DriverManager.getConnection(url, login, password);
    }

    public static void main(String[] args) {
        readProperties();
        int interval = Integer.valueOf(map.get("rabbit.interval"));
        try {
            Connection cn = connect();
            Scheduler sheduler  = StdSchedulerFactory.getDefaultScheduler();
            sheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connect", cn);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(interval)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            sheduler.scheduleJob(job, trigger);
            Thread.sleep(15000);
            sheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {

        private Timestamp date;

        public Rabbit() {
            date = new Timestamp(System.currentTimeMillis());
            System.out.println("Запуск программы " + date);
        }

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
            Connection connect = (Connection) context.getJobDetail().getJobDataMap().get("connect");
            try (PreparedStatement statement = connect.prepareStatement("insert into rabbit(created_date) values (?)")) {
                    statement.setTimestamp(1, date);
                    statement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
