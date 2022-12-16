package com.urlshortenerapi.services.models;

import com.urlshortenerapi.enums.UrlOperations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History {
    private Long id;
    private UrlOperations operation;
    private Url url;
}
