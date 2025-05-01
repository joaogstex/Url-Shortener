package com.urlshortener.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urlshortener.urlshortener.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);
}
