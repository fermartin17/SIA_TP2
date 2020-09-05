package TP;

import TP.Configuration.ConfigurationFile;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        boolean writeToGraph = true;

        //CSVImportHelper csvImportHelper = new CSVImportHelper();
        //csvImportHelper.setData();


        Socket socket = null;
        OutputStream outputStream = null;
        //noinspection ConstantConditions
        if(writeToGraph) {
            try {
                socket = new Socket("localhost", 4444);
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file = new File("configuration.json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Gson gson = new Gson();
        ConfigurationFile conf = gson.fromJson(bufferedReader, ConfigurationFile.class);
        System.out.println("configuration loaded");
        Game game = new Game(conf, outputStream);
        System.out.println("game created");
        game.run();

        //noinspection ConstantConditions
        if(writeToGraph) {
            try {
                //noinspection ConstantConditions
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Gson gson = new Gson();
        //System.out.println(gson.toJson(new ConfigurationFile()));



    }
}
