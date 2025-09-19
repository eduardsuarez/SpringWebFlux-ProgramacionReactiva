package com.eduardsuarez.callbacks;

import com.eduardsuarez.database.Database;
import com.eduardsuarez.models.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class CallbacksExample {
    public static Flux<Videogame> callbacks() {
        return Database.getVideogamesFlux()
                .doOnSubscribe(subscription -> log.info("[doOnSubscribe]"))
                .doOnRequest(value -> log.info("[doOnRequest] {}", value))
                .doOnNext(videogame -> log.info("[doOnNext] {}", videogame.getName()))
                .doOnCancel(() -> log.warn("[doOnCancel]"))
                .doOnError(err -> log.error("[doOnError]", err))
                .doOnComplete(() -> log.info("[doOnComplete]: success"))
                .doOnTerminate(() -> log.info("[doOnTerminate]"))
                .doFinally(signal -> log.warn("[doFinally]: {}", signal));
    }
}
