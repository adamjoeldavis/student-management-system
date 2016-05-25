package com.davis.sms.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application entrypoint
 * 
 * @author Adam Davis
 */
@SpringBootApplication
public class Application
{
    public static void main(String... args) throws Exception
    {
        SpringApplication.run(Application.class, args);
    }
}
