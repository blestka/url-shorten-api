package com.urlshortenerapi.services.impl;

import com.urlshortenerapi.repositories.HistoryRepository;
import com.urlshortenerapi.services.StatisticsService;
import com.urlshortenerapi.services.models.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AggregatedStatisticsService implements StatisticsService {
    private final HistoryRepository historyRepository;

    @Override
    public List<Statistics> getStatistics() {
       return historyRepository.aggregateHistoryByUrlAndOperation();
    }
}
