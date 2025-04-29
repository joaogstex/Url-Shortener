package com.urlshortener.urlshortener.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortener.urlshortener.model.Url;
import com.urlshortener.urlshortener.repository.UrlRepository;
import com.urlshortener.urlshortener.service.UrlService;

@Service
public class UrlServiceImpl implements UrlService {
    
    @Autowired
    private UrlRepository urlRepository;

    @Override
    public String shortenUrl(String originalUrl) {
        String shortUrl = generateShortUrl();
        Url url = new Url();
        //remove as aspas no postman %22
        originalUrl = originalUrl.replace("\"", "").trim();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        urlRepository.save(url);
        return shortUrl;
    }

    @Override
    public String redirect(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        return url != null ? url.getOriginalUrl() : "Url not found";
    }

    private String generateShortUrl() {
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt, 36); //converte o número para base36 (a-z + 0-9)
    }
}
