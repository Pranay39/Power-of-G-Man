package com.example.geektrust.service;

import com.example.geektrust.utils.Axis;
import com.example.geektrust.utils.Direction;
import com.example.geektrust.utils.MoveConstants;

import java.util.List;

public class YPowerCalculatorService extends TurnService {
    @Override
    public int calculatePower(int movements, List<Character> directions, String sourceDirection) {
        int powerSpent = movements * MoveConstants.MOVE_COST;

        if (directions.contains(sourceDirection.charAt(0))) {
            return MoveConstants.INITIAL_POWER - powerSpent;
        }

        powerSpent += calculateTurnCost(Axis.Y, sourceDirection);
        return MoveConstants.INITIAL_POWER - powerSpent;
    }
}

