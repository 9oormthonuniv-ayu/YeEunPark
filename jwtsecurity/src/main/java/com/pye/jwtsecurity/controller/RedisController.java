package com.pye.jwtsecurity.controller;

import com.pye.jwtsecurity.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/set")
    public String setKey(@RequestParam String key, @RequestParam String value, @RequestParam long duration) {
        redisService.setData(key, value, duration);
        return "Saved key: " + key + " with value: " + value;
    }

    @GetMapping("/get")
    public String getKey(@RequestParam String key) {
        return redisService.getData(key);
    }

    @DeleteMapping("/delete")
    public String deleteKey(@RequestParam String key) {
        redisService.deleteData(key);
        return "Deleted key: " + key;
    }
}
