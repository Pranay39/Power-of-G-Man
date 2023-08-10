package com.example.geektrust.service;

import com.example.geektrust.utils.Axis;
import com.example.geektrust.utils.MoveConstants;

import java.util.List;

public class XYPowerCalculatorService extends TurnService {
    @Override
    public int calculatePower(int movements, List<Character> directions, String sourceDirection) {
        int powerSpent = movements * MoveConstants.MOVE_COST;

        if (directions.contains(sourceDirection.charAt(0))) {
            powerSpent += MoveConstants.TURN_COST;
        } else {
            powerSpent += calculateTurnCost(Axis.XY, sourceDirection);
        }

        return MoveConstants.INITIAL_POWER - powerSpent;
    }
}