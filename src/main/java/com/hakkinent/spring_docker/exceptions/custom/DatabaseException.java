package com.hakkinent.spring_docker.exceptions.custom;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message) {
        super(message);
    }
}
