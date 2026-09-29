package com.example.demo.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Logs a couple of JDK 25 language/runtime features at startup so they are
 * easy to spot when running the sample.
 */
@Component
public class JdkFeatureDemoRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(JdkFeatureDemoRunner.class);

    @Override
    public void run(ApplicationArguments args) {
        log.info("Running on {}", Runtime.version());

        Bicycle bicycle = new Bicycle(26);
        log.info("Built a bicycle with {} wheels, size {}in (flexible constructor bodies, JEP 513)",
                bicycle.wheels(), bicycle.wheelSizeInInches());

        ScopedValue<String> demoValue = ScopedValue.newInstance();
        ScopedValue.where(demoValue, "startup-demo")
                .run(() -> log.info("Scoped value bound: {} (JEP 506)", demoValue.get()));
    }
}
