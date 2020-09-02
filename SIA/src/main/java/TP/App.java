package TP;

import TP.Configuration.ConfigurationFile;
import TP.Constants.Constants;
import TP.Helpers.CSVImportHelper;
import TP.Interfaces.ICSVImportHelper;
import TP.Interfaces.ISelection;
import TP.Interfaces.IService;
import TP.Models.BasePlayer;
import TP.Models.Equipment;
import TP.Models.Genetics.Selections.EliteSelection;
import TP.Models.Genetics.Selections.RouletteSelection;
import TP.Services.RedisService;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        // Get configuration
        File file = new File("configuration.json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Gson gson = new Gson();
        ConfigurationFile conf = gson.fromJson(bufferedReader, ConfigurationFile.class);

       Game game = new Game(conf);

//        System.out.println(gson.toJson(new ConfigurationFile()));



    }
}
