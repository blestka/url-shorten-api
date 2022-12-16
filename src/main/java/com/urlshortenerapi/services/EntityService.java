package com.urlshortenerapi.services;

public interface EntityService<T> {
    T save(T entity);
}
