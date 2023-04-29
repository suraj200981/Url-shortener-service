package com.example.url.shortner.microservices.urlshortenerservice.proxy;

import com.example.url.shortner.microservices.urlshortenerservice.model.UrlDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "redirection-service", url = "localhost:8200")
public interface ProxyRequest {

    @RequestMapping("/redirect")
    void RequestRedirection (@RequestBody UrlDTO urlDTO);
}
