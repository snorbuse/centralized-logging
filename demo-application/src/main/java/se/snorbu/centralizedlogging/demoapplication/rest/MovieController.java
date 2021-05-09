package se.snorbu.centralizedlogging.demoapplication.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class MovieController {
    @GetMapping
    public Map<String, String> getMovieTips() {
        log.info("Fetching new movie");
        return Map.of("movie", "The Terminator");
    }
}
