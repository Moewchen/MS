package com.bht.meditrack.vitaldatenmanagement.exceptions;

public class VitaldatenNotFoundException extends RuntimeException {
    public VitaldatenNotFoundException(String message) {
        super(message);
    }
}