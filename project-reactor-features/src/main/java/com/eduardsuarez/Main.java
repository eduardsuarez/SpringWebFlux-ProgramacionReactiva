package com.eduardsuarez;


import com.eduardsuarez.error_handler.FallBackService;
import com.eduardsuarez.error_handler.HandleDisabledVideoGame;
import com.eduardsuarez.pipelines.PipelineAllComments;
import com.eduardsuarez.pipelines.PipelineSumAllPricesInDiscount;
import com.eduardsuarez.pipelines.PipelineTopSelling;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Main {
    public static void main(String[] args) {
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
        FallBackService.handleDisabledVideoGame()
                .subscribe(v -> log.info(v.toString()));

    }
}