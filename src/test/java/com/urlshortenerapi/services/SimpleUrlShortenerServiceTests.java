package com.urlshortenerapi.services;

import com.urlshortenerapi.exceptions.UrlNotFoundException;
import com.urlshortenerapi.services.impl.SimpleUrlShortenerService;
import com.urlshortenerapi.services.impl.UrlsService;
import com.urlshortenerapi.services.models.Url;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SimpleUrlShortenerServiceTests {
    @Mock
    private UrlsService urlsService;

    @InjectMocks
    private SimpleUrlShortenerService simpleUrlShortenerService;

    @Test
    public void shortenUrlTest_ExistentUrl() {
        String url = "test.com";
        String hash = "abc";
        when(urlsService.findByOrgUrl(url)).thenReturn(Optional.of(Url.builder().origUrl(url).shortenedPath(hash).build()));
        String shortUrl = simpleUrlShortenerService.shortenUrl(url);

        verify(urlsService, times(0)).save(any(Url.class));
        Assertions.assertTrue(shortUrl.endsWith("/" + hash));
    }

    @Test
    public void shortenUrlTest_NewUrl() {
        String url = "test.com";
        String hash = "abc";
        when(urlsService.findByOrgUrl(url)).thenReturn(Optional.empty());
        when(urlsService.save(any(Url.class))).thenReturn(Url.builder().shortenedPath(hash).build());
        String shortUrl = simpleUrlShortenerService.shortenUrl(url);

        verify(urlsService, times(1)).save(any(Url.class));
        Assertions.assertTrue(shortUrl.endsWith("/" + hash));
    }

    @Test
    public void resolveUrl_ExistentUrl() throws UrlNotFoundException {
        String url = "test.com";
        String hash = "abc";
        when(urlsService.findByHash(hash)).thenReturn(Optional.of(Url.builder().origUrl(url).shortenedPath(hash).build()));
        String origUrl = simpleUrlShortenerService.resolveOriginalUrl(hash);

        Assertions.assertEquals(url, origUrl);
    }

    @Test
    public void resolveUrl_HashNotFound() {
        String hash = "abc";
        when(urlsService.findByHash(hash)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UrlNotFoundException.class, () -> {
            simpleUrlShortenerService.resolveOriginalUrl(hash);
        });

        String expectedMessage = "Mapping not found for short url";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
