package com.urlshortenerapi.services.impl;

import com.urlshortenerapi.repositories.HistoryRepository;
import com.urlshortenerapi.repositories.models.HistoryEntity;
import com.urlshortenerapi.services.EntityService;
import com.urlshortenerapi.services.converters.HistoryConverter;
import com.urlshortenerapi.services.models.History;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistoryService implements EntityService<History> {
    private final HistoryRepository historyRepository;
    private final HistoryConverter historyConverter;

    @Transactional
    @Override
    public History save(History history) {
        if (history == null || history.getUrl() == null)
            return null;
        HistoryEntity historyEntity = historyConverter.toDao(history);
        return historyConverter.fromDao(historyRepository.save(historyEntity));
    }
}
