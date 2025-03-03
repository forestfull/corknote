package com.forestfull;

import com.forestfull.log.up.util.Log;
import com.forestfull.prop.TunnelingProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(value = {TunnelingProperties.class})
public class Application {
    private static final int THREAD_COUNT = 10;
    private static final int LOG_COUNT = 1000;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

        long single1 = singleThreadLogging();
        long multi1 = multiThreadLogging();
        long single2 = singleThreadLogging2();
        long multi2 = multiThreadLogging2();




        Log.info(single1, " ", single2);
        Log.info(multi1, " ", multi2);

    }

    private static long singleThreadLogging() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < LOG_COUNT * THREAD_COUNT; i++) {
            log.info("Single Thread Log message {}", i);
        }

        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

    private static long multiThreadLogging() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < LOG_COUNT; j++) {
                    log.info("Multi Thread Log message {}", j);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }
    private static long singleThreadLogging2() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < LOG_COUNT * THREAD_COUNT; i++) {
            Log.info("Single Thread Log message {}", i);
        }

        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

    private static long multiThreadLogging2() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < LOG_COUNT; j++) {
                    Log.info("Multi Thread Log message {}", j);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }
}