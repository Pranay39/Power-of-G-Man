package com.example.geektrust.service;

import com.example.geektrust.utils.Axis;
import com.example.geektrust.utils.Direction;
import com.example.geektrust.utils.MoveConstants;

import java.util.List;

public abstract class TurnService implements PowerCalculatorService {
    protected int calculateTurnCost(Axis axis, String sourceDirection) {
        if (axis == Axis.X && (sourceDirection.equals(String.valueOf(Direction.NORTH.getSymbol())) || sourceDirection.equals(String.valueOf(Direction.SOUTH.getSymbol())))) {
            return MoveConstants.TURN_COST;
        }
        if (axis == Axis.Y && (sourceDirection.equals(String.valueOf(Direction.EAST.getSymbol())) || sourceDirection.equals(String.valueOf(Direction.WEST.getSymbol())))) {
            return MoveConstants.TURN_COST;
        }
        else {
            return MoveConstants.TURN_COST * MoveConstants.TWO_TURNS;
        }
    }
}
