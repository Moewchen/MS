package com.bht.MediTrack.Vitaldatenmanagement.exceptions;

public class VitaldatenNotFoundException extends RuntimeException {
    public VitaldatenNotFoundException(String message) {
        super(message);
    }
}