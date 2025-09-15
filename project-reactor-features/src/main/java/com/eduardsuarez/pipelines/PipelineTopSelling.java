package com.eduardsuarez.pipelines;

import com.eduardsuarez.database.Database;
import com.eduardsuarez.models.Videogame;
import reactor.core.publisher.Flux;

public class PipelineTopSelling {
    public static Flux<String> getTopSellingVideogames(){
        return Database.getVideogamesFlux()
                .filter(videogame -> videogame.getTotalSold() > 80 )
                .map(Videogame::getName);
    }
}
