package com.pye.jwtsecurity.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisCacheController {

    @Cacheable(value = "myCache", key = "#key")
    @GetMapping("/cache")
    public String getFromCache(@RequestParam String key) {
        System.out.println("데이터베이스에서 조회 중...");
        return "Data for Key: " + key;
    }

    @CacheEvict(value = "myCache", key = "#key")
    @GetMapping("/cache/evict")
    public String evictCache(@RequestParam String key) {
        return "Cache for Key: " + key + " has been cleared.";
    }
}
