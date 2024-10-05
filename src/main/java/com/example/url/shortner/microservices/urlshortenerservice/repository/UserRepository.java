package com.example.url.shortner.microservices.urlshortenerservice.repository;

import com.example.url.shortner.microservices.urlshortenerservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAddressIgnoreCase(String emailAddress);

}


