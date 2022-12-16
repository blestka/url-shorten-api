package com.urlshortenerapi.controllers.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateShortUrlResponse {
    private String shortUrl;
}
