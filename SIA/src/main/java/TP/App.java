package TP;

import TP.Helpers.CSVImportHelper;
import TP.Interfaces.ICSVImportHelper;

public class App {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        ICSVImportHelper importer = new CSVImportHelper();

        importer.setData();

        System.out.println("finished");
    }
}
