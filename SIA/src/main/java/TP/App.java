package TP;

import TP.Configuration.ConfigurationFile;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class App {
    public static void main(String[] args) {

        boolean multipleRuns = true;
        boolean writeToGraph = false;

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
        //noinspection ConstantConditions
        if(!multipleRuns) {
            File file1 = new File("warrior_config.json");
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            assert bufferedReader != null;
            ConfigurationFile conf = gson.fromJson(bufferedReader, ConfigurationFile.class);
            if (conf.getDataConf().isSetData()) {
                Game game = new Game(conf.getDataConf());
                return;
            } else {
                System.out.println("configuration loaded");
                List<ConfigurationFile> confs = new ArrayList<>();
                confs.add(conf);
                List<OutputStream> outputStreams = new ArrayList<>();
                outputStreams.add(outputStream);
                Game game = new Game(confs, outputStreams);
                System.out.println("game created");
                game.run();

                if (writeToGraph) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            }else{
                multipleRuns(args);
            }
    }

    public static void multipleRuns(String[] args){
        List<File> files = new ArrayList<>(args.length);
        //for(String path : args){
        //    files.add(new File(path));
        //}
        files.add(new File("warrior_config.json"));
        files.add(new File("archer_config.json"));
        files.add(new File("defender_config.json"));
        files.add(new File("infiltrate_config.json"));
        files.add(new File("warrior_fillall.json"));
        files.add(new File("warrior_fillparent.json"));
        List<BufferedReader> bufferedReaders = new ArrayList<>(files.size());
        for(File f : files){
            try {
                bufferedReaders.add(new BufferedReader(new FileReader(f)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Gson gson = new Gson();
        List<ConfigurationFile> configs = new ArrayList<>(files.size());
        for(int i = 0 ; i < bufferedReaders.size(); i++){
            configs.add(gson.fromJson(bufferedReaders.get(i), ConfigurationFile.class));
            System.out.println("configuration " + i + " loaded");
        }
        List<OutputStream> outputStreams = new ArrayList<>();
        //creando los archivos de salida
        for(int i = 0; i < files.size(); i++){
            try {
                outputStreams.add(new FileOutputStream(new File("run" + i + ".txt")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Game game = new Game(configs, outputStreams);
        System.out.println("game created");
        game.run();
  }

}
