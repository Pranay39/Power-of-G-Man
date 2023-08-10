package com.example.geektrust.service;

import com.example.geektrust.utils.Direction;
import com.example.geektrust.utils.MoveConstants;

import java.util.List;

public class YPowerCalculatorService implements PowerCalculatorService {
    @Override
    public int calculatePower(int movements, List<Character> directions, String sourceDirection) {
        // Calculate power for Axis Y based on provided parameters
        int powerSpent = 0;

        // Calculate power spent on movements
        int movementPower = movements * MoveConstants.MOVE_COST;
        powerSpent += movementPower;

        // Check if the source direction is contained in the given directions
        if (directions.contains(sourceDirection.charAt(0))) {
            return MoveConstants.INITIAL_POWER - movementPower;
        }

        // Check if source direction is EAST or WEST
        if (sourceDirection.equals(String.valueOf(Direction.EAST.getSymbol())) ||
                sourceDirection.equals(String.valueOf(Direction.WEST.getSymbol()))) {
            powerSpent += MoveConstants.TURN_COST;
        }else{
            powerSpent += MoveConstants.TURN_COST * MoveConstants.TWO_TURNS;

        }

        return MoveConstants.INITIAL_POWER - powerSpent;
    }
}

