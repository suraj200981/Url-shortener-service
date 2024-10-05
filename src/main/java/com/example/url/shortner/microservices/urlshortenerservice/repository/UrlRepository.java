package com.example.url.shortner.microservices.urlshortenerservice.repository;

import com.example.url.shortner.microservices.urlshortenerservice.model.UrlDTO;
import com.example.url.shortner.microservices.urlshortenerservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<UrlDTO,Integer> {
    List<UrlDTO> findByUserId(Long userId);

}
