package com.reeinvent.dictionary.exceptions;

public class SameWordAsSynonymException extends RuntimeException {
    public SameWordAsSynonymException(String message) {
        super(message);
    }
}
