package com.urlshortenerapi.services;

import com.urlshortenerapi.exceptions.UrlNotFoundException;

public interface UrlShortenerService {
    String shortenUrl(String url);
    String resolveOriginalUrl(String hash) throws UrlNotFoundException;
}
