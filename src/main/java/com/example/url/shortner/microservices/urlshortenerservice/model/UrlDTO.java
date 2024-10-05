package com.example.url.shortner.microservices.urlshortenerservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "urls") // Make sure this matches the correct table name
public class UrlDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "shortened_url")
    private String shortenedUrl;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "click_count")
    private Integer clickCount = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Define many-to-one relationship with User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true) // Set nullable = true for guest users
    private User user;

}
