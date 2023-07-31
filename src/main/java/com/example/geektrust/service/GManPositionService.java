package com.example.geektrust.service;

import com.example.geektrust.model.DirectionConstants;
import com.example.geektrust.model.GManPositionDTO;
import com.example.geektrust.model.MoveConstants;

public class GManPositionService {
    public static int calculateRemainingPower(GManPositionDTO source, GManPositionDTO destination){
        int deltaX = Math.abs(destination.getxCoordinate() - source.getxCoordinate());
        int deltaY = Math.abs(destination.getyCoordinate() - source.getyCoordinate());

        // Calculate the total moves needed to reach the destination
        int totalMoves = deltaX + deltaY;

        // Calculate the total cost of moving (power spent on moves)
        int moveCost = totalMoves * MoveConstants.MOVE_COST;

        // Calculate the number of turns G-Man can make (minimum of deltaX and deltaY)
        int turns = Math.min(deltaX, deltaY);
        // Calculate the cost of turns (power spent on turns)
        int turnCost = turns * MoveConstants.TURN_COST;
        turnCost = turnCost / 2;


//         Adjust turnCost based on the direction G-Man is facing at the source
        char sourceDirection = source.getDirection().charAt(0);
        if(sourceDirection == DirectionConstants.NORTH || sourceDirection == DirectionConstants.SOUTH){
            turnCost += (deltaX - turns) * MoveConstants.TURN_COST;
        } else if (sourceDirection == DirectionConstants.EAST || sourceDirection == DirectionConstants.WEST){
            turnCost += (deltaY - turns) * MoveConstants.TURN_COST;
        }
        turnCost = turns * MoveConstants.TURN_COST;
        // Calculate the remaining power after reaching the destination
        return MoveConstants.INITIAL_POWER - (moveCost +  turnCost);
    }

    public void calculateTurns(){

    }
}
