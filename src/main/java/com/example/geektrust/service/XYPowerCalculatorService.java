package com.example.geektrust.service;

import com.example.geektrust.utils.MoveConstants;

import java.util.List;

    public class XYPowerCalculatorService implements PowerCalculatorService {
        @Override
        public int calculatePower(int movements, List<Character> directions, String sourceDirection) {
            // Calculate power for Axis XY based on provided parameters
            int powerSpent = 0;

            // Calculate power spent on movements
            int movementPower = movements * MoveConstants.MOVE_COST;
            powerSpent += movementPower;

            // Check if the source direction is contained in the given directions
            if (directions.contains(sourceDirection.charAt(0))) {
                powerSpent += MoveConstants.TURN_COST;
            }else{
                powerSpent += MoveConstants.TURN_COST * MoveConstants.TWO_TURNS;
            }
            return MoveConstants.INITIAL_POWER - powerSpent;
        }
    }