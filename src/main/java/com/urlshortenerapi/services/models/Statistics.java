package com.urlshortenerapi.services.models;

import com.urlshortenerapi.enums.UrlOperations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    private String origUrl;
    private UrlOperations operation;
    private Long count;
}
