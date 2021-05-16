package se.snorbu.centralizedlogging.demoapplication.rest;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.snorbu.centralizedlogging.demoapplication.repo.MovieRepo;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
public class MovieController {

    @Autowired
    private MovieRepo movieRepo;

    @GetMapping
    public Map<String, String> getMovieTips() {
        MDC.put("correlationId", UUID.randomUUID().toString());
        log.info("Fetching new movie");
        return Map.of("movie", movieRepo.findRandomMovie());
    }
}
