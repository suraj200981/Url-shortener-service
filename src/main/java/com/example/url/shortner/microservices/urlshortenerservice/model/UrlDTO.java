package com.example.url.shortner.microservices.urlshortenerservice.model;

import lombok.Data;

@Data
public class UrlDTO {

    private String originalUrl;
    private String prefix;
}
