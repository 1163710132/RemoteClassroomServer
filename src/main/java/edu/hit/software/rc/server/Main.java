package edu.hit.software.rc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.hit.software.rc.server")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
