package TP.Helpers;

import TP.Constants.Constants;
import TP.Interfaces.IService;
import TP.Models.Equipment;
import TP.Services.RedisService;
import com.google.gson.Gson;

public class CSVImportHelper {

    private IService redisService;
    private Gson gson;

    public CSVImportHelper(){
        this.redisService = new RedisService();
        this.gson = new Gson();
    }

    public void getData(String path){
        //How serialize and deserializa data
        String json = this.gson.toJson("listaDePontos");


        this.redisService.setData(Constants.Equipment.front,json);
    }
}
