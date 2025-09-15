package com.eduardsuarez.pipelines;

import com.eduardsuarez.database.Database;
import com.eduardsuarez.models.Videogame;
import reactor.core.publisher.Mono;

public class PipelineSumAllPricesInDiscount {
    public static Mono<Double> getAllPricesinDiscount() {
        return Database.getVideogamesFlux()
                .filter(Videogame::getIsDiscount)
                .map(Videogame::getPrice)
                .reduce(0.0, Double::sum);

    }
}
