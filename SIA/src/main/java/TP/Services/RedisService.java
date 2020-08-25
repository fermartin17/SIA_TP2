package TP.Services;

import TP.Interfaces.IService;
import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

@Getter
@Setter
public class RedisService implements IService {

    Jedis jedis;

    public RedisService() {
        this.jedis = new Jedis("redis://127.0.0.1:6379");
    }

    @Override
    public void setData(String key, String data) {
        jedis.sadd(key, data);
    }


    @Override
    public Set<String> getByIndex(String key) {
        return jedis.smembers(key);
    }
}
