package com.eduardsuarez.error_handler;

import com.eduardsuarez.database.Database;
import com.eduardsuarez.models.Console;
import com.eduardsuarez.models.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
@Slf4j
public class FallBackService {
    public static Flux<Videogame> handleDisabledVideoGame() {
        return Database.getVideogamesFlux()
                .handle((vg, sink) -> {
                    if (Console.DISABLE == vg.getConsole()) {
                        sink.error(new RuntimeException("VideoGame is disabled"));
                        return;
                    }
                    sink.next(vg);
                })
                .retry(5)
                .onErrorResume(error -> {
                    log.error("Database is falling");
                    return Database.fluxFallBack;
                })
                .cast(Videogame.class);
    }
}
