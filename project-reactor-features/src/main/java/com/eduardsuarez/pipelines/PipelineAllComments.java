package com.eduardsuarez.pipelines;

import com.eduardsuarez.database.Database;
import com.eduardsuarez.models.Review;
import reactor.core.publisher.Flux;

public class PipelineAllComments {
    public static Flux<String> getAllComments () {
        return Database.getVideogamesFlux()
                .flatMap(videogame -> Flux.fromIterable(videogame.getReviews()))
                .map(Review::getComment);
    }
}
