import entities.Entity;
import constants.Constants;
import service.DataHandler;
import service.XslxFileReader;

import java.io.File;
import java.util.*;

public class Main {
    private static File xlsxLocation;
    public static List<Entity> entities;
    private static List<Entity> filteredData;
    static DataHandler dataHandler = new DataHandler();

    public static void main(String[] args) throws Exception {
        xlsxLocation = new File(Constants.FILE_PATH);
        entities = XslxFileReader.extractEntitiesFromFile(xlsxLocation);
        filteredData = DataHandler.filterData(entities, Constants.EVENT_WIKI);

        System.out.print("\n\n");

        Map<String, Long> data1 = DataHandler.calculateAbsoluteFrequency(entities, Constants.EVENT_WIKI);
        System.out.println("ABSOLUTE FREQUENCY\n");
        data1.forEach((key, value) -> System.out.println(key + ":" + value));

        Map<String, Double> data2 = DataHandler.calculateRelativeFrequency(entities, Constants.EVENT_WIKI);
        System.out.println("\nRELATIVE:\n");
        data2.forEach((key, value) -> System.out.println(key + ":" + value));

        System.out.println("\nMode:");
        System.out.println(DataHandler.findMode(entities, Constants.EVENT_WIKI));

        System.out.println("\nVariance: ");
        System.out.println(dataHandler.calculateVariance(entities));
        System.out.print("\n\n");
    }
}