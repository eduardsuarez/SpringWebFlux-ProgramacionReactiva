package com.eduardsuarez;

import lombok.extern.java.Log;

import java.util.Locale;


@Log//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Publishers
        final ReactiveStream<String> stringStream = new ReactiveStream<>();
        final ReactiveStream<Integer> intStream = new ReactiveStream<>();

        final String subsName1 = "Subscriber1";
        final String subsName2 = "Subscriber2";
        final String subsName3 = "Subscriber3";
        final String subsName4 = "Subscriber4";

        // Subscriber for stringStream
        final Subscriber<String> strSubs1 = new SubscriberImpl<>(
                str -> "Length " + str.length(),
                subsName1
        );
        // Subscriber for stringStream
        final Subscriber<String> strSubs2 = new SubscriberImpl<>(
                String::toUpperCase,
                subsName2
        );

        // Subscriber for intStream
        final Subscriber<Integer> intSubs1 = new SubscriberImpl<>(
                num -> "Value: " + num,
                subsName3
        );

        // Subscriber for intStream
        final Subscriber<Integer> intSubs2 = new SubscriberImpl<>(
                num -> "Square: " + (num * num),
                subsName4
        );

        stringStream.subscribe(strSubs1)
                .subscribe(strSubs2);

        intStream.subscribe(intSubs1)
                .subscribe(intSubs2);
        log.info("---[Strings]---");
        stringStream.emit("Hola a todos, soy ya saben quién?");
        stringStream.emit("Exacto, el ingeniero");
        stringStream.emit("Eduard Suárez");

        log.info("---[numbers]---");
        intStream.emit(156);
        intStream.emit(862);
        intStream.emit(3);

        stringStream.unsubscriber(strSubs2);
        intStream.unsubscriber(intSubs1);
        intStream.unsubscriber(intSubs2);


    }
}