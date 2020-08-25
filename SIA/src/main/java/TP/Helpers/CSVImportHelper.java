package TP.Helpers;

import TP.Interfaces.IService;
import TP.Services.RedisService;

public class CSVImportHelper {

    private IService redisService;

    public CSVImportHelper(){
        this.redisService = new RedisService();
    }
}
