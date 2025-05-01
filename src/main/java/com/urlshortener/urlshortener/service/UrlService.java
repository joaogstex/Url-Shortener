package com.urlshortener.urlshortener.service;

import java.net.URI;

public interface UrlService {
    public String shortenUrl(String originalUrl);
    public URI redirect(String shortUrl);
}
