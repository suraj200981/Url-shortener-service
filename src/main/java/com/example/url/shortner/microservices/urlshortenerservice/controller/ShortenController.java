package com.example.url.shortner.microservices.urlshortenerservice.controller;

import com.example.url.shortner.microservices.urlshortenerservice.Service.ShortenService;
import com.example.url.shortner.microservices.urlshortenerservice.model.UrlDTO;
import com.example.url.shortner.microservices.urlshortenerservice.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ShortenController {


    @Autowired
    ShortenService shortenService;

    @Autowired
    UrlRepository urlRepository;

    @PostMapping("/shorten")
    public void shortenUrl(@RequestBody UrlDTO urlDTO) {

        UrlDTO urlDTToSave = new UrlDTO();
        urlDTToSave.setOriginalUrl(urlDTO.getOriginalUrl());
        urlDTToSave.setShortenedUrl(shortenService.shortenUrl(urlDTO));
        urlDTToSave.setPrefix(urlDTO.getPrefix());
        urlDTToSave.setCreatedAt(urlDTO.getCreatedAt());
        urlDTO.setClickCount(urlDTO.getClickCount());

        urlRepository.save(urlDTToSave);
        log.info("Url saved in database");

    }

}
