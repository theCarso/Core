package me.thecarso.core.utils;

import lombok.Getter;

import java.util.HashMap;

public class TPRequest {

    private static @Getter
    HashMap<String, TPRequest> tpRequests= new HashMap<>();;

    public enum TPType {
        TPA, TPAHERE;
    }

    private @Getter String from;
    private @Getter String to;
    private @Getter TPType type;

    public TPRequest(String from, String to, TPType type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }
}
