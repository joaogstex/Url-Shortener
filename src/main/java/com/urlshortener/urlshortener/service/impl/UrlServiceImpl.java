package com.urlshortener.urlshortener.service.impl;

import java.net.URI;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortener.urlshortener.exception.UrlNotFoundException;
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
    public URI redirect(String shortUrl) {
        Url originalUrl = urlRepository.findByShortUrl(shortUrl)
        .orElseThrow(() -> new UrlNotFoundException("URL não encontrada"));
        //orElseThrow existe apenas no Optional, lembrar de quando for utilizar
        //passar isso com o tipo do Model no Repository
        return URI.create(originalUrl.getOriginalUrl());
    }

    private String generateShortUrl() {
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt, 36); //converte o número para base36 (a-z + 0-9)
    }
}
