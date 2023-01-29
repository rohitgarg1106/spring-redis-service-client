package service.redis.spring.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.redis.spring.client.redis.RedisManager;
import service.redis.spring.client.request.SetRequest;

@Slf4j
@Service
public class RedisService {
    @Autowired
    private RedisManager redisManager;

    public String getKey(String key){
        return redisManager.getKey(key);
    }

    public void setKeyWithExpiry(SetRequest request) {
        redisManager.setKeyWithExpiry(request.getKey(), request.getValue(), request.getTtl());
    }
}
