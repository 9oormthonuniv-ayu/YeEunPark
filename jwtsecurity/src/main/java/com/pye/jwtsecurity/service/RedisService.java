package com.pye.jwtsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void setData(String key, String value, long duration) {
        redisTemplate.opsForValue().set(key, value, duration, TimeUnit.SECONDS);
    }

    public String getData(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? value.toString() : "Key not found";
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }
}
