package com.jbnu.ideahub.entry.exception;

public class NoSuchEntryException extends RuntimeException {

    public NoSuchEntryException() {
        super("존재하지 않는 작품입니다.");
    }
}
