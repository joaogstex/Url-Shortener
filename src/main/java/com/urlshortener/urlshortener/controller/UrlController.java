package com.urlshortener.urlshortener.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.urlshortener.service.UrlService;

import jakarta.servlet.http.HttpServletResponse;

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
    public void redirector(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        if (!"Url not found".equals(urlService.redirect(shortUrl))) {
            response.sendRedirect(urlService.redirect(shortUrl));
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "URL não encontrada.");
        }
        //return urlService.redirect(shortUrl);
    }
}
