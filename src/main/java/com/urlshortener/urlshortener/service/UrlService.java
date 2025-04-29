package com.urlshortener.urlshortener.service;

public interface UrlService {
    public String shortenUrl(String originalUrl);
    public String redirect(String shortUrl);
}
