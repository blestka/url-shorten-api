package com.urlshortenerapi.services.converters;

import com.urlshortenerapi.repositories.models.UrlEntity;
import com.urlshortenerapi.services.models.Url;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UrlsConverter implements Converter<UrlEntity, Url> {
    @Override
    public Url fromDao(UrlEntity entity) {
        if (Objects.isNull(entity))
            return null;
        return Url.builder()
                .id(entity.getId())
                .origUrl(entity.getOrigUrl())
                .shortenedPath(entity.getShortenedPath())
                .build();
    }

    @Override
    public UrlEntity toDao(Url model) {
        if (Objects.isNull(model))
            return null;
        return UrlEntity.builder()
                .id(model.getId())
                .origUrl(model.getOrigUrl())
                .shortenedPath(model.getShortenedPath())
                .build();
    }
}
