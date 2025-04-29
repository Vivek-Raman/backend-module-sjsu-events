package dev.vivekraman.sjsu.events.controller;

import dev.vivekraman.sjsu.events.config.Constants;
import dev.vivekraman.localist.sdk.LocalistAPI;
import dev.vivekraman.localist.sdk.model.Event;
import dev.vivekraman.monolith.annotation.MonolithController;
import dev.vivekraman.monolith.model.Response;
import dev.vivekraman.monolith.model.ResponseList;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@MonolithController(moduleName = Constants.MODULE_NAME)
@RequiredArgsConstructor
public class TestController {
  private final LocalistAPI localistAPI;
  private final Scheduler scheduler;

  @GetMapping
  public Mono<ResponseList<Event>> fetchEvents(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size) {
    ZonedDateTime start = ZonedDateTime.now();
    ZonedDateTime end = start.plus(1, ChronoUnit.DAYS);
    return localistAPI.getEvents(start, end, page, size)
        .collectList()
        .map((events) -> {
          ResponseList<Event> response = ResponseList.of(events);
          response.setData(events);
          response.setPage(null); // TODO: set page info
          response.setSize(null);
          response.setTotal(null);
          return response;
        }).subscribeOn(scheduler);
  }
}
