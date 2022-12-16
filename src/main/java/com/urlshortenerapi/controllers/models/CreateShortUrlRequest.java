package com.urlshortenerapi.controllers.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateShortUrlRequest {
    @NotBlank
    private String url;
}
