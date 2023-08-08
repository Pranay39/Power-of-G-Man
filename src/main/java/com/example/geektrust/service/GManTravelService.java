package com.example.geektrust.service;

import com.example.geektrust.utils.Axis;
import com.example.geektrust.utils.Direction;
import com.example.geektrust.utils.MoveConstants;

import java.util.ArrayList;

public class GManTravelService {

    /**
     * Calculate the remaining power after traveling specified movements and directions,
     * taking into account the source direction and the axis of movement.
     *
     * @param movements       The total number of movements.
     * @param directions      List of directions taken during the travel.
     * @param sourceDirection The initial direction of the source.
     * @param axis            The axis of movement (X, Y, or XY).
     * @return The remaining power after the travel.
     */
    public static int travel(int movements, ArrayList<Character> directions, String sourceDirection, Axis axis) {
        if (axis.equals(Axis.Y)) {
            if (directions.contains(sourceDirection.charAt(0))) {
                return MoveConstants.INITIAL_POWER - (movements * MoveConstants.MOVE_COST);
            }
            if (sourceDirection.equals(String.valueOf(Direction.EAST.getSymbol())) || sourceDirection.equals(String.valueOf(Direction.WEST.getSymbol()))) {
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + MoveConstants.TURN_COST);
            } else {
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + (MoveConstants.TURN_COST * 2));
            }
        } else if (axis.equals(Axis.X)) {
            if (directions.contains(sourceDirection.charAt(0))) {
                return MoveConstants.INITIAL_POWER - (movements * MoveConstants.MOVE_COST);
            }
            if (sourceDirection.equals(String.valueOf(Direction.NORTH.getSymbol())) || sourceDirection.equals(String.valueOf(Direction.SOUTH.getSymbol()))) {
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + MoveConstants.TURN_COST);
            } else {
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + (MoveConstants.TURN_COST * MoveConstants.TWO_TURNS));
            }
        } else if (axis.equals(Axis.XY)) {
            if (directions.contains(sourceDirection.charAt(0))) {
                // Calculate power after movement and one turn
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + MoveConstants.TURN_COST);
            } else {
                // Calculate power after movement and two turns
                return MoveConstants.INITIAL_POWER - ((movements * MoveConstants.MOVE_COST) + (MoveConstants.TURN_COST * MoveConstants.TWO_TURNS));
            }
        } else {
            throw new UnsupportedOperationException("Invalid axis provided.");
        }
    }


}
