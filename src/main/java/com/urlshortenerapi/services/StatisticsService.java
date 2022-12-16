package com.urlshortenerapi.services;

import com.urlshortenerapi.services.models.Statistics;

import java.util.List;

public interface StatisticsService {
    List<Statistics> getStatistics();
}
