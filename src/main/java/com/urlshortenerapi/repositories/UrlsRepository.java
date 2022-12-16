package com.urlshortenerapi.repositories;

import com.urlshortenerapi.repositories.models.UrlEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UrlsRepository extends CrudRepository<UrlEntity, Long> {
    Optional<UrlEntity> findFirstByShortenedPath(String hash);
    Optional<UrlEntity> findFirstByOrigUrl(String url);
}
