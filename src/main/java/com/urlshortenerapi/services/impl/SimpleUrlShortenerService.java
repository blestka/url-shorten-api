package com.urlshortenerapi.services.impl;

import com.urlshortenerapi.exceptions.UrlNotFoundException;
import com.urlshortenerapi.services.UrlShortenerService;
import com.urlshortenerapi.services.models.Url;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SimpleUrlShortenerService implements UrlShortenerService {
    private final UrlsService urlsService;

    @Value("${app.base.url}")
    private String baseUrl;

    @Transactional
    public String shortenUrl(String url) {
        Optional<Url> existentUrl = urlsService.findByOrgUrl(url);
        return baseUrl + "/" +
                existentUrl
                        .orElseGet(() -> urlsService.save(Url.builder().origUrl(url).build()))
                        .getShortenedPath();
    }

    @Override
    public String resolveOriginalUrl(String hash) throws UrlNotFoundException {
        return urlsService
                .findByHash(hash)
                .map(Url::getOrigUrl)
                .orElseThrow(() -> new UrlNotFoundException("Mapping not found for short url"));
    }
}
