package dev.vivekraman.sjsu.events.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.vivekraman.*")
public class BackendModuleSjsuEventsApplication {
  public static void main(String[] args) {
    SpringApplication.run(BackendModuleSjsuEventsApplication.class, args);
  }
}
