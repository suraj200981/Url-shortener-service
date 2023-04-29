package com.example.url.shortner.microservices.urlshortenerservice.repository;

import com.example.url.shortner.microservices.urlshortenerservice.model.UrlDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlDTO,Integer> {

}
