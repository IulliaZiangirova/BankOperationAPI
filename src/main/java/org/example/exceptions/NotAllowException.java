package org.example.exceptions;

public class NotAllowException extends RuntimeException{
    public NotAllowException(String message) {
        super(message);
    }
}
