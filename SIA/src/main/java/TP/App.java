package TP;

import TP.Configuration.ConfigurationFile;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        // Get configuration
        File file = new File("configuration.json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Gson gson = new Gson();
        ConfigurationFile conf = gson.fromJson(bufferedReader, ConfigurationFile.class);

       Game game = new Game(conf);

       game.run();

//       System.out.println(gson.toJson(new ConfigurationFile()));



    }
}
