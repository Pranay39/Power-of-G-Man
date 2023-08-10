package com.example.geektrust;

import com.example.geektrust.io.FileReaderUtility;
import com.example.geektrust.model.GManPositionDTO;
import com.example.geektrust.service.GManPositionService;
import java.util.List;


public class Main {
    GManPositionService gManPositionService = new GManPositionService();
    public static void main(String[] args) {
        Main main = new Main();
        try{
            main.processInputFile(args[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void processInputFile(String filename) {
        if (filename.isEmpty()) {
            System.out.println("Error while  processing arguments");
            return;
        }
        try {
            List<String> inputLines = FileReaderUtility.readInputFromFile(filename);

            GManPositionDTO sourcePosition = null;
            GManPositionDTO destinationPosition = null;

            for (String input : inputLines) {
                String[] cmd = input.split(" ");

                switch (cmd[0]) {
                    case "SOURCE":
                        sourcePosition = new GManPositionDTO(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), cmd[3]);
                        break;
                    case "DESTINATION":
                        destinationPosition = new GManPositionDTO(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), null);
                        break;

                    case "PRINT_POWER":
                        if (sourcePosition != null && destinationPosition != null) {
                            int remainingPower = gManPositionService.calculateTurnsAndSteps(sourcePosition, destinationPosition);
                            System.out.println("POWER " + remainingPower);
                        } else {
                            System.out.println("ERROR : Invalid source and destination positions");
                        }
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error while running the application");
        }
    }
}




