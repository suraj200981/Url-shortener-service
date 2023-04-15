package com.example.url.shortner.microservices.urlshortenerservice.Service;

import com.example.url.shortner.microservices.urlshortenerservice.model.UrlDTO;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShortenService {

    // choose a Character random from this String
    String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    public String shortenUrl(UrlDTO urlDTO) {
        StringBuilder generatedString= new StringBuilder();

        for(int x=0; x<5; x++){
            generatedString.append(alphaNumericString.charAt(new Random().nextInt(alphaNumericString.length())));
        }
        if(urlDTO.getPrefix().isEmpty()){
            return urlDTO.getOriginalUrl()+ "/"+generatedString;
        }else{
            return urlDTO.getPrefix()+urlDTO.getOriginalUrl()+"/"+generatedString;
        }
    }

}
