package com.kalah.core.domain;

public interface Pit {

    int count();

    int takeAll();

    int increment();

    Pit next();

    Pit opposite();

}
