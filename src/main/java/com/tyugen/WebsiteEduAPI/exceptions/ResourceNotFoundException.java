package com.tyugen.WebsiteEduAPI.exceptions;

/**
 * This class represents an exception that is thrown when a resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructs a new ResourceNotFoundException object with the specified message.
     *
     * @param message the message to be used by this exception
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
