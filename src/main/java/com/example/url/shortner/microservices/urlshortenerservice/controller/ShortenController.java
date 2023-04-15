package com.example.url.shortner.microservices.urlshortenerservice.controller;

import com.example.url.shortner.microservices.urlshortenerservice.Service.ShortenService;
import com.example.url.shortner.microservices.urlshortenerservice.model.UrlDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenController {


    @Autowired
    ShortenService shortenService;

    @PostMapping("/shorten")
    public void shortenUrl(@RequestBody UrlDTO urlDTO) {
        System.out.println(shortenService.shortenUrl(urlDTO));

    }

}
