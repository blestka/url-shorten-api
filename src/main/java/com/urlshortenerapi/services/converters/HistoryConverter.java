package com.urlshortenerapi.services.converters;

import com.urlshortenerapi.repositories.models.HistoryEntity;
import com.urlshortenerapi.services.models.History;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoryConverter implements Converter<HistoryEntity, History>{
    private final UrlsConverter urlsConverter;

    @Override
    public History fromDao(HistoryEntity historyEntity) {
        return History.builder()
                .id(historyEntity.getId())
                .operation(historyEntity.getOperation())
                .url(urlsConverter.fromDao(historyEntity.getUrl()))
                .build();
    }

    @Override
    public HistoryEntity toDao(History history) {
        return HistoryEntity.builder()
                .id(history.getId())
                .operation(history.getOperation())
                .url(urlsConverter.toDao(history.getUrl()))
                .build();
    }
}
