package com.cathay.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDTO {
    @JsonProperty("Keys")
    private String[] keys;

    @JsonProperty("From")
    private String from;

    @JsonProperty("To")
    private String to;

    // Getters 和 Setters
    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}