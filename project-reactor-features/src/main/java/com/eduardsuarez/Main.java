package com.eduardsuarez;

import com.eduardsuarez.callbacks.CallbacksExample;
import com.eduardsuarez.database.Database;
import com.eduardsuarez.error_handler.FallBackService;
import com.eduardsuarez.error_handler.HandleDisabledVideoGame;
import com.eduardsuarez.models.Console;
import com.eduardsuarez.models.Videogame;
import com.eduardsuarez.pipelines.PipelineAllComments;
import com.eduardsuarez.pipelines.PipelineSumAllPricesInDiscount;
import com.eduardsuarez.pipelines.PipelineTopSelling;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Slf4j
public class Main {

    public static boolean videogameForConsole(
        Videogame videogame,
        Console console
    ) {
        return (
            videogame.getConsole() == console ||
            videogame.getConsole() == Console.ALL
        );
    }

    public static void main(String[] args) throws InterruptedException {
        //        Mono<String> mono = Mono.just("Hello world")
        //                .doOnNext(value -> log.info("[onNext]: " + value))
        //                .doOnSuccess(value -> log.info("[sucess]: " + value))
        //                .doOnError(err -> log.info("[Error]: " + err.getMessage()));
        //
        //        mono.subscribe(
        //                data -> log.info("Recibiendo datos: " + data),
        //                err -> log.info("Error "+ err.getMessage()),
        //                () -> log.info("Complete success")
        //        );
        // Publisher
        //        Flux<String> flux = Flux.just("Java", "Spring", "Reactor", "R2DBC")
        //                .doOnNext(value -> log.info("[onNext]: "+ value))
        //                .doOnError(err -> log.info("[error]: " + err.getMessage()))
        //                .doOnComplete(() -> log.info("[onComplete]: Completado correctamente"));
        //
        //        // Consumer
        //        flux.subscribe(
        //                data -> log.info("recibiendo: " + data),
        //                err -> log.info("Error: "+ err.getMessage()),
        //                () -> log.info("Completado correctamente")
        //        );
        //        PipelineTopSelling.getTopSellingVideogames()
        //                .subscribe(System.out::println);
        //
        //        PipelineSumAllPricesInDiscount.getAllPricesinDiscount()
        //                .subscribe(System.out::println);
        //
        //        PipelineAllComments.getAllComments()
        //                .subscribe(System.out::println);
        //
        //        Flux<String> fluxA = Flux.just("1", "2");
        //        Flux<String> fluxB = Flux.just("A","B","C");
        //
        //        Flux<String> combine = fluxA.flatMap(strA -> fluxB.map(strB -> strA + "-" + strB));
        //
        //        combine.map(String::toLowerCase)
        //                .doOnNext(System.out::println)
        //                .subscribe();

        // Operador Zip
        // calls ms
        //        Flux<String> fluxShipments = Flux.just("Shipment1", "Shipment2", "Shipment3").delayElements(Duration.ofMillis(120));
        //        Flux<String> fluxWarehouse = Flux.just("stock1", "stock2", "stock3").delayElements(Duration.ofMillis(50));
        //        Flux<String> fluxPayments = Flux.just("pay1", "pay2", "pay3").delayElements(Duration.ofMillis(150));
        //        Flux<String> fluxConfirm = Flux.just("confirm1", "confirm2", "confirm3").delayElements(Duration.ofMillis(20));
        //
        //        Flux<String> reportFlux = Flux.zip(fluxShipments, fluxWarehouse, fluxPayments, fluxConfirm)
        //                .map(tuple -> tuple.getT1() + " " +
        //                        tuple.getT2() + " " +
        //                        tuple.getT3()+ " " +
        //                        tuple.getT4());
        //
        //        reportFlux.
        //                doOnNext(System.out::println)
        //                .blockLast();
        // Lanzar excepciones y manejar errores
        //        HandleDisabledVideoGame.handleDisabledVideoGame()
        //                .subscribe(System.out::println);
        //        HandleDisabledVideoGame.handleDisabledVideoGameDefault()
        //                .subscribe(v -> log.info(v.toString()));
        //        FallBackService.handleDisabledVideoGame()
        //                .subscribe(v -> log.info(v.toString()));
        //        HandleDisabledVideoGame.handleDisabledVideoGameDefault()
        //                .subscribe(v -> log.info(v.toString()));
        //        CallbacksExample.callbacks()
        //                .subscribe(data -> log.debug(data.getName()),
        //                        err -> log.error(err.getMessage()),
        //                        () -> log.debug("Finished subs"));

        //        Database.getVideogamesFlux()
        //                .filterWhen(videogame -> Mono.deferContextual(contextView -> {
        //                    var userdId = contextView.getOrDefault("userId", "0");
        //                    if (userdId.startsWith("1")){
        //                        log.info("Entró a 1");
        //                        return Mono.just(videogameForConsole(videogame, Console.XBOX));
        //                    }
        //                    else if (userdId.startsWith("2")){
        //                        log.info("Entro a 2");
        //                        return Mono.just(videogameForConsole(videogame, Console.PLAYSTATION));
        //                    }
        //                    return Mono.just(false);
        //                }))
        //                .contextWrite(Context.of("userId", "1003242"))
        //                .subscribe(vg -> log.info("Recommended name {} console {}", vg.getName(), vg.getConsole()));
        //

        log.info("COld Publisher");

        Flux<Integer> coldPublisher = Flux.range(1, 10);
        log.info("Subs 1 subscribed");
        coldPublisher.subscribe(n -> log.info("[s1] {}", n));

        log.info("Subs 2 subscribed");
        coldPublisher.subscribe(n -> log.info("[s2] {}", n));

        log.info("Subs 3 subscribed");
        coldPublisher.subscribe(n -> log.info("[s3] {}", n));

        log.info("Hot Publisher");
        Flux<Long> hotPublisher = Flux.interval(Duration.ofSeconds(1))
            .publish()
            .autoConnect();
        log.info("Subs 4 subscribed");
        hotPublisher.subscribe(n -> log.info("[s4] {}", n));
        Thread.sleep(2000);
        log.info("Subs 5 subscribed");
        hotPublisher.subscribe(n -> log.info("[s5] {}", n));
        Thread.sleep(1000);
        log.info("Subs 6 subscribed");
        hotPublisher.subscribe(n -> log.info("[s6] {}", n));
        Thread.sleep(10000);
    }
}
