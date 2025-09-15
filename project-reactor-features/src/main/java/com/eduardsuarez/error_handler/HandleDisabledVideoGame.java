package com.eduardsuarez.error_handler;

import com.eduardsuarez.database.Database;
import com.eduardsuarez.models.Console;
import com.eduardsuarez.models.Review;
import com.eduardsuarez.models.Videogame;
import reactor.core.publisher.Flux;

import java.util.List;

public class HandleDisabledVideoGame {
    public static final Videogame DEFAULT_VIDEOGAME =
            Videogame.builder()
                    .name("Default")
                    .price(0.0)
                    .console(Console.ALL)
                    .reviews(List.of(

                    ))
                    .officialWebsite("https://www.default.com")
                    .isDiscount(false)
                    .totalSold(0)
                    .build();

    public static Flux<Videogame> handleDisabledVideoGame() {
        return Database.getVideogamesFlux()
                .handle((vg, sink) -> {
                    if (Console.DISABLE == vg.getConsole()) {
                        sink.error(new RuntimeException("VideoGame is disabled"));
                        return;
                    }
                    sink.next(vg);
                })
                .onErrorResume(error -> {
                    System.out.println("Error detected: " + error.getMessage());
                    return Flux.merge(Database.getVideogamesFlux(), Database.fluxAssassinsDefault);
                })
                .cast(Videogame.class)
                .distinct(Videogame::getName);
    }

    public static Flux<Videogame> handleDisabledVideoGameDefault() {
        return Database.getVideogamesFlux()
                .handle((vg, sink) -> {
                    if (Console.DISABLE == vg.getConsole()) {
                        sink.error(new RuntimeException("VideoGame is disabled"));
                        return;
                    }
                    sink.next(vg);
                })
                .onErrorReturn(DEFAULT_VIDEOGAME)
                .cast(Videogame.class);
    }
}
