package com.kalah.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    @JsonProperty
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
