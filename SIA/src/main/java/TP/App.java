package TP;

import TP.Configuration.ConfigurationFile;
import TP.Helpers.CSVImportHelper;
import TP.Interfaces.ICSVImportHelper;
import TP.Interfaces.ISelection;
import TP.Models.BasePlayer;
import TP.Models.Genetics.Selections.EliteSelection;
import TP.Models.Genetics.Selections.RouletteSelection;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("configuration.json");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        Gson gson = new Gson();

        Object json = gson.fromJson(bufferedReader, ConfigurationFile.class);


        System.out.println(gson.toJson(new ConfigurationFile()));

    }
}
