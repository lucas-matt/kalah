package com.kalah.core.domain;

public class House extends Sowable {

    public House(int idx, int stones) {
        super(idx, stones);
    }

    @Override
    boolean isPit() {
        return false;
    }

}
