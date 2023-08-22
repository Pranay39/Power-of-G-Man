package com.example.geektrust.service;

import com.example.geektrust.utils.Axis;
import com.example.geektrust.utils.MoveConstants;
import java.util.List;

public abstract class PowerCalculatorBase implements TurnService {
    protected int calculatePowerCommon(int movements, List<Character> directions, String sourceDirection, Axis axis) {
        int powerSpent = movements * MoveConstants.MOVE_COST;

        powerSpent += (!directions.contains(sourceDirection.charAt(0))) ? calculateTurnCost(axis, sourceDirection) : (axis.equals(Axis.XY) ? MoveConstants.TURN_COST : 0);

        return MoveConstants.INITIAL_POWER - powerSpent;
    }

}
