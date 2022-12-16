package com.urlshortenerapi.services;

import com.urlshortenerapi.repositories.HistoryRepository;
import com.urlshortenerapi.repositories.models.HistoryEntity;
import com.urlshortenerapi.repositories.models.UrlEntity;
import com.urlshortenerapi.services.converters.HistoryConverter;
import com.urlshortenerapi.services.impl.HistoryService;
import com.urlshortenerapi.services.models.History;
import com.urlshortenerapi.services.models.Url;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceTests {
    @Captor
    ArgumentCaptor<HistoryEntity> captor;
    @Mock
    private HistoryConverter historyConverter;
    @Mock
    private HistoryRepository historyRepository;
    @InjectMocks
    private HistoryService historyService;

    @Test
    public void testSaveHistoryRecord() {
        String operation = "READ";

        Url url = Url.builder().id(123L).build();
        UrlEntity urlEntity = UrlEntity.builder().id(123L).build();

        History history = History.builder().operation(operation).url(url).build();
        HistoryEntity historyEntity = HistoryEntity.builder().operation(operation).url(urlEntity).build();

        when(historyConverter.toDao(history)).thenReturn(historyEntity);
        historyService.save(history);

        verify(historyRepository, times(1)).save(captor.capture());
        assertEquals(url.getId(), captor.getValue().getUrl().getId());
        assertEquals(operation, captor.getValue().getOperation());
    }

    @Test
    public void testSaveHistoryRecord_NoUrl() {
        String operation = "READ";
        History history = History.builder().operation(operation).url(null).build();
        historyService.save(history);

        verify(historyRepository, times(0)).save(any());
    }
}
