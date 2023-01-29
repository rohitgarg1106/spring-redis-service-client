package service.redis.spring.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.redis.spring.client.request.SetRequest;
import service.redis.spring.client.service.RedisService;

import java.awt.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @GetMapping(value = "/getKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getKey(@RequestParam("key") String key) {
        try {
            log.info("received request for fetching string value");
            return new ResponseEntity(redisService.getKey(key), HttpStatus.OK);
        } catch(Exception e){
            log.error("error : {}", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping(value = "/setKeyWithExpiry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setKeyWithExpiry(@RequestBody SetRequest request) {
        try {
            log.info("received request for setting string value");
            redisService.setKeyWithExpiry(request);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            log.error("error : {}", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
