package com.hakkinent.spring_docker.exceptions.custom;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
