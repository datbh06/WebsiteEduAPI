package com.tyugen.WebsiteEduAPI.exceptions;

/**
 * This class represents an exception that is thrown when a resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
