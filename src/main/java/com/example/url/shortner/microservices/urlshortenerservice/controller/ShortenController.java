package com.example.url.shortner.microservices.urlshortenerservice.controller;

import com.example.url.shortner.microservices.urlshortenerservice.Service.ShortenService;
import com.example.url.shortner.microservices.urlshortenerservice.model.UrlDTO;
import com.example.url.shortner.microservices.urlshortenerservice.repository.UrlRepository;
import com.example.url.shortner.microservices.urlshortenerservice.model.User;
import com.example.url.shortner.microservices.urlshortenerservice.repository.UserRepository;
import com.example.url.shortner.microservices.urlshortenerservice.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
public class ShortenController {

    @Autowired
    ShortenService shortenService;

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlDTO urlDTO, @RequestHeader(value = "Authorization", required = false) String token) {
        User user = null;

        // Check if the Authorization header is present and extract user if available
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            String userEmail = jwtUtil.extractUsername(jwtToken);

            // Find the user by email
            user = userRepository.findByEmailAddressIgnoreCase(userEmail);
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        }

        // Create and populate a new UrlDTO
        UrlDTO urlDTOToSave = new UrlDTO();
        urlDTOToSave.setOriginalUrl(urlDTO.getOriginalUrl());
        urlDTOToSave.setShortenedUrl(shortenService.shortenUrl(urlDTO));
        urlDTOToSave.setPrefix(urlDTO.getPrefix());
        urlDTOToSave.setCreatedAt(urlDTO.getCreatedAt());
        urlDTOToSave.setClickCount(urlDTO.getClickCount());

        // If user is logged in, associate the URL with the user
        if (user != null) {
            urlDTOToSave.setUser(user);
            log.info("Url saved in database for user: " + user.getEmailAddress());
        } else {
            log.info("Url saved in database for guest user.");
        }

        // Save the URL in the database
        try {
            urlRepository.save(urlDTOToSave);
            return new ResponseEntity<>("Shortened url: " + urlDTOToSave.getShortenedUrl(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error saving URL: ", e);
            return new ResponseEntity<>("Failed to save URL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
