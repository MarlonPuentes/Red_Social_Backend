package com.uptc.exception;

public class ShareNotFoundException extends RuntimeException {
    public ShareNotFoundException() {
    }

    public ShareNotFoundException(String message) {
        super(message);
    }
}
