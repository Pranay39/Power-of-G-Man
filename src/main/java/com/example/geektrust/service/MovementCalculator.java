package com.example.geektrust.service;

import com.example.geektrust.model.MoveConstants;

import java.util.ArrayList;

public class MovementCalculator {
    private final String axis;
    private final ArrayList<String> directions;
    private final String direction;

    public MovementCalculator(String axis, ArrayList<String> directions, String direction) {
        this.axis = axis;
        this.directions = directions;
        this.direction = direction;
    }


    public int calculateRemainingPower(int movements) {
        if (axis.equals("Y")) {
            if (directions.contains(direction)) {
                return movements * MoveConstants.MOVE_COST;
            }
            if (direction.equals("E") || direction.equals("W")) {
                return (movements * MoveConstants.MOVE_COST) + MoveConstants.TURN_COST;
            } else {
                return (movements * MoveConstants.MOVE_COST) + MoveConstants.MOVE_COST;
            }
        }

        if (axis.equals("X")) {
            if (directions.contains(direction)) {
                return movements * MoveConstants.MOVE_COST;
            }
            if (direction.equals("N") || direction.equals("S")) {
                return (movements * MoveConstants.MOVE_COST) + MoveConstants.TURN_COST;
            } else {
                return (movements * MoveConstants.MOVE_COST) + MoveConstants.MOVE_COST;
            }
        }

        if (axis.equals("XY")) {
            if (directions.contains(direction)) {
                return (movements * MoveConstants.MOVE_COST) + MoveConstants.TURN_COST;
            } else {
                return (movements * MoveConstants.MOVE_COST) + MoveConstants.MOVE_COST;
            }
        }

        return 0;
    }

}
