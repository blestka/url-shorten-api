package com.urlshortenerapi.exceptions;

public class UrlNotFoundException extends Exception {
    public UrlNotFoundException(String message) {
        super(message);
    }
}
