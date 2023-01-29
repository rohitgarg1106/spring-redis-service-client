package service.redis.spring.client.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import service.redis.spring.client.common.CommonUtils;

@Slf4j
public class RedisManager {

    private StatefulRedisConnection<String,String> connection;

    public RedisManager(String redisHost, Integer redisPort, String redisPassword) {
        RedisURI redisURI = RedisURI.builder().withHost(redisHost).withPort(redisPort).build();
        if(!CommonUtils.isStringEmpty(redisPassword)){
            redisURI.setPassword(redisPassword);
        }
        RedisClient client = RedisClient.create(redisURI);
        this.connection = client.connect();
    }

    public String getKey(String key){
        try{
            RedisCommands<String, String> commands = this.connection.sync();
            return commands.get(key);
        }
        catch (Exception e){
            log.error("RedisManager::getKey exception {}",e);
            throw e;
        }
    }

    public void setKeyWithExpiry(String key, String value, int expiry){
        try{
            RedisCommands<String, String> commands = this.connection.sync();
            commands.setex(key, expiry, value);
        }
        catch (Exception e){
            log.error("RedisManager::setKeyWithExpiry exception {}",e);
        }
    }


}
