package com.urlshortenerapi.services.impl;

import com.urlshortenerapi.repositories.UrlsRepository;
import com.urlshortenerapi.repositories.models.UrlEntity;
import com.urlshortenerapi.services.EntityService;
import com.urlshortenerapi.services.converters.UrlsConverter;
import com.urlshortenerapi.services.models.Url;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlsService implements EntityService<Url> {
    private final UrlsConverter urlsConverter;
    private final UrlsRepository urlsRepository;

    @Override
    @Transactional
    public Url save(Url url) {
        String hash = RandomStringUtils.random(10, true, true);
        url.setShortenedPath(hash);
        UrlEntity urlEntity = urlsConverter.toDao(url);
        return urlsConverter.fromDao(urlsRepository.save(urlEntity));
    }

    public Optional<Url> findByOrgUrl(String url) {
        return urlsRepository.findFirstByOrigUrl(url)
                .map(urlsConverter::fromDao);
    }

    public Optional<Url> findByHash(String hash) {
        return urlsRepository.findFirstByShortenedPath(hash)
                .map(urlsConverter::fromDao);
    }

}
