package com.uptc.exception;

public class ShareExistsException extends RuntimeException {
    public ShareExistsException() {
    }

    public ShareExistsException(String message) {
        super(message);
    }
}
