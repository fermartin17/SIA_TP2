package TP.Services;

import TP.Interfaces.IService;
import TP.Models.Equipment;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RedisService implements IService {

    private Jedis jedis;
    private Gson gson;

    public RedisService() {
        this.jedis = new Jedis("redis://127.0.0.1:6379");
    }

    @Override
    public void setData(String key, String data) {
        jedis.sadd(key, data);
    }


    @Override
    public Set<Equipment> getByIndex(String key) {
        Set<String> data = this.jedis.smembers(key);
        Set<Equipment> equipment = new HashSet<Equipment>();

        for(String s : data){
            equipment.add(this.gson.fromJson(s,Equipment.class));
        }
        return equipment;
    }
}
