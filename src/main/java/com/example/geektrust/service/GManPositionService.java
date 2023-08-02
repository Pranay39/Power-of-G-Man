package com.example.geektrust.service;

import com.example.geektrust.model.DirectionConstants;
import com.example.geektrust.model.GManPositionDTO;
import com.example.geektrust.model.MoveConstants;

import java.util.ArrayList;

public class GManPositionService {

    public static int calculateTurnsAndSteps(GManPositionDTO source, GManPositionDTO destination) {
        
        if (source.getxCoordinate() == destination.getxCoordinate() && source.getyCoordinate() == destination.getyCoordinate()) {
            return MoveConstants.INITIAL_POWER;
        }

        ArrayList<String> directions = new ArrayList<>();
        int remainingPower;

        if (source.getxCoordinate() == destination.getxCoordinate() || source.getxCoordinate() == source.getyCoordinate()) {
            if (source.getyCoordinate() < destination.getyCoordinate()) {
                directions.add("N");
            } else {
                directions.add("S");
            }
            remainingPower = travel(Math.abs(source.getyCoordinate() - destination.getyCoordinate()), directions, source.getDirection(), "Y");
            return remainingPower;
        }

        if (source.getyCoordinate() == destination.getyCoordinate() || source.getxCoordinate() == source.getyCoordinate()) {
            if (source.getxCoordinate() < destination.getxCoordinate()) {
                directions.add("E");
            } else {
                directions.add("W");
            }
            remainingPower = travel(Math.abs(source.getxCoordinate() - destination.getxCoordinate()), directions, source.getDirection(), "X");
            return remainingPower;
        } else {
            if (source.getxCoordinate() < destination.getxCoordinate()) {
                if (source.getyCoordinate() < destination.getyCoordinate()) {
                    directions.add("N");
                    directions.add("E");
                } else {
                    directions.add("S");
                    directions.add("E");
                }
            }
            if (source.getxCoordinate() > destination.getxCoordinate()) {
                if (source.getyCoordinate() > destination.getyCoordinate()) {
                    directions.add("S");
                    directions.add("W");
                } else {
                    directions.add("N");
                    directions.add("W");
                }
            }
            remainingPower = travel((Math.abs(source.getxCoordinate() - destination.getxCoordinate()) + Math.abs(source.getyCoordinate() - destination.getyCoordinate())), directions, source.getDirection(), "XY");
            return remainingPower;
        }

    }

    public static int travel(int movements, ArrayList<String> directions, String direction, String axis) {
        MovementCalculator movementCalculator = new MovementCalculator(axis, directions, direction);
        return 200 - movementCalculator.calculateRemainingPower(movements);
    }




}