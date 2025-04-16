package dev.vivekraman.sjsu.events.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SjsuEventsConfig {
  @Bean
  public GroupedOpenApi sjsuEventsApiGroup() {
    return GroupedOpenApi.builder()
        .group(Constants.MODULE_NAME)
        .packagesToScan("dev.vivekraman.module.controller")
        .build();
  }
}
