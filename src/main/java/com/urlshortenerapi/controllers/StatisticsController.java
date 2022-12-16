package com.urlshortenerapi.controllers;

import com.urlshortenerapi.services.impl.AggregatedStatisticsService;
import com.urlshortenerapi.services.models.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatisticsController {
    private final AggregatedStatisticsService aggregatedStatisticsService;

    @GetMapping("/stats")
    public List<Statistics> getStatistics() {
        return aggregatedStatisticsService.getStatistics();
    }
}
