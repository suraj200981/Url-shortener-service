package com.example.url.shortner.microservices.urlshortenerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "urls")
public class UrlDTO {

    //random id generator
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String originalUrl;
    //hide prefix in database
    private String prefix;
    private int clickCount;

//    private List<String> ipAddress;
    private Date createdAt;
    private String shortenedUrl;

}
