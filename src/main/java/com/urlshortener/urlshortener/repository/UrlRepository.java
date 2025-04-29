package com.urlshortener.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urlshortener.urlshortener.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShortUrl(String shortUrl);
}
