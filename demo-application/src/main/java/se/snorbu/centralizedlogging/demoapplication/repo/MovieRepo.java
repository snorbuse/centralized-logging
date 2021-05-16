package se.snorbu.centralizedlogging.demoapplication.repo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class MovieRepo {

    private List<String> movies = List.of(
            "The Terminator",
            "The good, the bad, the ugly",
            "Conan",
            "The last action hero"
    );
    private Random random = new Random();

    public String findRandomMovie() {
        log.info("Finding a (new) random movie");
        return findMovie();
    }

    private String findMovie() {
        try {
            String movie = movies.get(random.nextInt(movies.size() * 2));
            log.info("Found movie: {}", movie);
            return movie;
        } catch (Exception e) {
            log.error("Couldn't find movie, got exception", e);
            return "N/A";
        }
    }
}
