package com.urlshortenerapi.repositories;

import com.urlshortenerapi.repositories.models.HistoryEntity;
import com.urlshortenerapi.services.models.Statistics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<HistoryEntity, Long> {
    @Query(value = "select new com.urlshortenerapi.services.models.Statistics(u.origUrl, h.operation, count(h.operation))" +
            " from HISTORY h " +
            "left join URLS u on h.url.id = u.id " +
            "group by h.url.id, h.operation")
    List<Statistics> aggregateHistoryByUrlAndOperation();
}
