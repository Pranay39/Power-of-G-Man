package com.example.geektrust;

import com.example.geektrust.io.FileReaderClass;
import com.example.geektrust.model.GManPositionDTO;
import com.example.geektrust.service.GManPositionService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try{
            processInputFile(args[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void processInputFile(String filename) {
        if (filename.isEmpty()) {
            System.out.println("Error while  processing arguments");
            return;
        }
        try {
            List<String> inputLines = FileReaderClass.readInputFromFile(filename);

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
                            int remainingPower = GManPositionService.calculateTurnsAndSteps(sourcePosition, destinationPosition);
                            System.out.println("POWER " + remainingPower);
                        } else {
                            System.out.println("ERROR : Invalid source and destination positions");
                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




