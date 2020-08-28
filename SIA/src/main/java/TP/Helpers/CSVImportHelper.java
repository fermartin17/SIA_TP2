package TP.Helpers;

import TP.Constants.Constants;
import TP.Interfaces.ICSVImportHelper;
import TP.Interfaces.IService;
import TP.Models.Equipment;
import TP.Services.RedisService;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVImportHelper implements ICSVImportHelper {

    private IService redisService;
    private Gson gson;

    public CSVImportHelper(){
        this.redisService = new RedisService();
        this.gson = new Gson();
    }

    @Override
    public void setData(){

        Equipment eq = new Equipment();
        String json;

        String tsvFile = "/home/fer/Documents/SIA/TP2/fulldata/guantes.tsv";
        BufferedReader br = null;
        String line = "";
        String tvsSplitBy = "\t";
        Boolean resp;

        try {

            br = new BufferedReader(new FileReader(tsvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] equipment = line.split(tvsSplitBy);
                eq.setId(Integer.parseInt(equipment[0]));

//                eq.setName(Constants.Equipment.helmet);
//                eq.setName(Constants.Equipment.front);
//                eq.setName(Constants.Equipment.weapons);
//                eq.setName(Constants.Equipment.boots);
                eq.setName(Constants.Equipment.gloves);

                eq.setHealth(Double.parseDouble(equipment[5]));
                eq.setAgility(Double.parseDouble(equipment[2]));
                eq.setStrength(Double.parseDouble(equipment[1]));
                eq.setResistence(Double.parseDouble(equipment[4]));
                eq.setPericia(Double.parseDouble(equipment[3]));

                json = this.gson.toJson(eq);

//                this.redisService.setData(Constants.Equipment.helmet,json);
//                this.redisService.setData(Constants.Equipment.front,json);
//                this.redisService.setData(Constants.Equipment.weapons,json);
//                this.redisService.setData(Constants.Equipment.boots,json);
                this.redisService.setData(Constants.Equipment.gloves,json);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
