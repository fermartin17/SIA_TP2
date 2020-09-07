package TP.Services;

import TP.Interfaces.IService;
import TP.Models.Equipment;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class RedisService implements IService {

    private Jedis jedis;
    private Gson gson;
    private Map<Integer, Equipment> helmets;
    private Map<Integer, Equipment> fronts;
    private Map<Integer, Equipment> gloves;
    private Map<Integer, Equipment> weapons;
    private Map<Integer, Equipment> boots;

    public RedisService() {
        this.jedis = new Jedis("redis://127.0.0.1:6379");
        this.gson = new Gson();
        this.helmets = new HashMap<Integer, Equipment>();
        this.fronts = new HashMap<Integer, Equipment>();
        this.gloves = new HashMap<Integer, Equipment>();
        this.weapons = new HashMap<Integer, Equipment>();
        this.boots = new HashMap<Integer, Equipment>();
    }

    @Override
    public void setData(String key, String data) {
        jedis.sadd(key, data);
    }


    @Override
    public Map<Integer,Equipment> getData(String key) {
        Set<String> data = this.jedis.smembers(key);
        Map<Integer,Equipment> equipment = new HashMap<Integer,Equipment>();


        for(String s : data){
            Equipment aux = this.gson.fromJson(s,Equipment.class);
            equipment.put(aux.getId(),aux);
        }
        return equipment;
    }

    @Override
    public void deleteByKey(String key){
        this.jedis.del(key);
    }
}
