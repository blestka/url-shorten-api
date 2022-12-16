package com.urlshortenerapi.controllers;

import com.urlshortenerapi.controllers.models.CreateShortUrlRequest;
import com.urlshortenerapi.controllers.models.CreateShortUrlResponse;
import com.urlshortenerapi.exceptions.UrlNotFoundException;
import com.urlshortenerapi.services.UrlShortenerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.HttpStatus.PERMANENT_REDIRECT;

@RestController
@RequiredArgsConstructor
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

    @PostMapping(value = "/short-urls", produces = "application/json")
    public CreateShortUrlResponse shortenUrl(@Valid @RequestBody CreateShortUrlRequest createShortUrlRequest) {
        return CreateShortUrlResponse.builder()
                .shortUrl(urlShortenerService.shortenUrl(createShortUrlRequest.getUrl()))
                .build();
    }

    @GetMapping("/{hash}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable("hash") String hash) throws UrlNotFoundException {
        return ResponseEntity.status(PERMANENT_REDIRECT)
                .location(URI.create(urlShortenerService.resolveOriginalUrl(hash)))
                .build();
    }
}
