package com.example.geektrust.service;

import com.example.geektrust.exception.InvalidCoordinatesException;
import com.example.geektrust.exception.InvalidDirectionException;
import com.example.geektrust.model.GManPositionDTO;
import com.example.geektrust.utils.MoveConstants;

import java.util.ArrayList;

public class GManPositionService {

    public static int calculateTurnsAndSteps(GManPositionDTO source, GManPositionDTO destination) throws InvalidCoordinatesException {

        if (!isValidCoordinates(source) || !isValidCoordinates(destination)) {
            throw new InvalidCoordinatesException("Invalid coordinates provided.");
        }
        if (!source.getDirection().equals("N") && !source.getDirection().equals("S") && !source.getDirection().equals("E") && !source.getDirection().equals("W")) {
            throw new InvalidDirectionException("Invalid source direction provided.");
        }


        int movementX = Math.abs(source.getxCoordinate() - destination.getxCoordinate());
        int movementY = Math.abs(source.getyCoordinate() - destination.getyCoordinate());

        if (source.getxCoordinate() == destination.getxCoordinate() && source.getyCoordinate() == destination.getyCoordinate()) {
            return MoveConstants.INITIAL_POWER;
        }

        ArrayList<String> directions = new ArrayList<>();
        int remainingPower;

        if (source.getxCoordinate() == destination.getxCoordinate()) {
            if (source.getyCoordinate() > destination.getyCoordinate()) {
                directions.add("S");
                return travel(movementY, directions, source.getDirection(), "Y");
            } else {
                directions.add("N");
                return travel(movementY, directions, source.getDirection(), "Y");
            }
        }

        if (source.getyCoordinate() == destination.getyCoordinate()) {
            if (source.getxCoordinate() > destination.getxCoordinate()) {
                directions.add("W");
                return travel(movementX, directions, source.getDirection(), "X");
            } else {
                directions.add("E");
                return travel(movementX, directions, source.getDirection(), "X");

            }
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
            remainingPower = travel(movementX + movementY, directions, source.getDirection(), "XY");
            return remainingPower;
        }
    }


    public static int travel(int movements, ArrayList<String> directions, String sourceDirection, String axis) {
        if(axis.equals("Y")){
            if(directions.contains(sourceDirection)){
                return MoveConstants.INITIAL_POWER - (movements * MoveConstants.MOVE_COST);
            }
            if(sourceDirection.equals("E") || sourceDirection.equals("W")){
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + MoveConstants.TURN_COST);
            }else {
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + (MoveConstants.TURN_COST * 2));
            }
        }
        if(axis.equals("X")){
            if(directions.contains(sourceDirection)){
                return MoveConstants.INITIAL_POWER - (movements * MoveConstants.MOVE_COST);
            }
            if(sourceDirection.equals("N") || sourceDirection.equals("S")) {
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + MoveConstants.TURN_COST);
            }else{
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + (MoveConstants.TURN_COST * 2));
            }
        }
        if(axis.equals("XY")){
            if(directions.contains(sourceDirection)){
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + MoveConstants.TURN_COST);
            }else{
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + (MoveConstants.TURN_COST * 2));
            }
        }
        return -1;
    }

    private static boolean isValidCoordinates(GManPositionDTO position) {
        // Check if the position is within the grid limits (6x6)
        return position.getxCoordinate() >= 0 && position.getxCoordinate() <= 6 &&
                position.getyCoordinate() >= 0 && position.getyCoordinate() <= 6;
    }
}