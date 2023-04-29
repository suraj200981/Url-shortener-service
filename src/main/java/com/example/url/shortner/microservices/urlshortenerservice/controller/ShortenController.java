package com.example.url.shortner.microservices.urlshortenerservice.controller;

import com.example.url.shortner.microservices.urlshortenerservice.Service.ShortenService;
import com.example.url.shortner.microservices.urlshortenerservice.model.UrlDTO;
import com.example.url.shortner.microservices.urlshortenerservice.proxy.ProxyRequest;
import com.example.url.shortner.microservices.urlshortenerservice.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
public class ShortenController {


    @Autowired
    ShortenService shortenService;

    @Autowired
    UrlRepository urlRepository;


    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlDTO urlDTO) {

        UrlDTO urlDTToSave = new UrlDTO();
        urlDTToSave.setOriginalUrl(urlDTO.getOriginalUrl());
        urlDTToSave.setShortenedUrl(shortenService.shortenUrl(urlDTO));
        urlDTToSave.setPrefix(urlDTO.getPrefix());
        urlDTToSave.setCreatedAt(urlDTO.getCreatedAt());
        urlDTToSave.setClickCount(urlDTO.getClickCount());

        urlRepository.save(urlDTToSave);
        log.info("Url saved in database");

        return new ResponseEntity<>("Shortened url: "+ urlDTToSave.getShortenedUrl(), HttpStatus.OK);

        //send url to redirect service
//        proxyRequest.RequestRedirection(urlDTToSave);

    }

}
