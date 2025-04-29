package dev.vivekraman.sjsu.events.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import dev.vivekraman.localist.sdk.LocalistAPI;
import dev.vivekraman.localist.sdk.internal.LocalistAPIClient;
import dev.vivekraman.localist.sdk.internal.LocalistProperties;

@Configuration
public class SjsuEventsConfig {
  @Bean
  public GroupedOpenApi sjsuEventsApiGroup() {
    return GroupedOpenApi.builder()
        .group(Constants.MODULE_NAME)
        .packagesToScan("dev.vivekraman.sjsu.events.controller")
        .build();
  }

  @Bean
  public LocalistAPI localistAPI(
      @Value("${PORT:8080}") String port, LocalistProperties props) {
    WebClient.Builder builder = WebClient.builder()
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.ORIGIN, "http://localhost:" + port)
        .defaultHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
        .defaultHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS")
        .defaultHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*")
        .defaultHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
    return new LocalistAPIClient(builder, props);
  }
}
