package com.eduardsuarez;

public interface Subscriber <T> {
    void onNext(T next);
    String getName();
}
