package com.davis.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application entrypoint
 * 
 * @author Adam Davis
 */
@SpringBootApplication
// @EntityScan(basePackageClasses = EntityBase.class)
public class Application
{
    public static void main(String... args) throws Exception
    {
        SpringApplication.run(Application.class, args);
    }
}
