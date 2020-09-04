package TP;

import TP.Configuration.ConfigurationFile;
import TP.Helpers.CSVImportHelper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        //CSVImportHelper csvImportHelper = new CSVImportHelper();
        //csvImportHelper.setData();

        ConfigurationFile configuration;
        File file = new File("configuration.json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Gson gson = new Gson();
        ConfigurationFile conf = gson.fromJson(bufferedReader, ConfigurationFile.class);
        System.out.println("configuration loaded");
        Game game = new Game(conf);
        System.out.println("game created");
        game.run();

//       System.out.println(gson.toJson(new ConfigurationFile()));



    }
}
