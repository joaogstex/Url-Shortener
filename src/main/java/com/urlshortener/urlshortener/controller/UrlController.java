package com.urlshortener.urlshortener.controller;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.urlshortener.service.UrlService;

@RestController
@RequestMapping()
public class UrlController {

    @Autowired
    private UrlService urlService;
    
    @PostMapping("/shorten")
    public String shortenerUrl(@RequestBody String originalUrl) {
        return urlService.shortenUrl(originalUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirector(@PathVariable String shortUrl) throws IOException {
        URI redirectUri = urlService.redirect(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(redirectUri).build();
    }
}
