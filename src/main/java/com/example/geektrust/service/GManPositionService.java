package com.example.geektrust.service;

import com.example.geektrust.model.GManPositionDTO;
import com.example.geektrust.utils.Axis;
import com.example.geektrust.utils.Direction;
import com.example.geektrust.utils.MoveConstants;

import java.util.ArrayList;
import java.util.List;


public class GManPositionService {

//    GManTravelService travelCalculator = new GManTravelService();
    ValidationService validationService = new ValidationService();
    TravelCalculator travelCalculator = new TravelCalculator();

    /**
     * Calculate the number of turns and steps needed for the G-Man to move from the source position to the destination position on the grid.
     *
     * @param source      The source position of the G-Man.
     * @param destination The destination position of the G-Man.
     * @return The remaining power after the travel.
     */
    public int calculateTurnsAndSteps(GManPositionDTO source, GManPositionDTO destination)  {
        validationService.validateCoordinates(source);
        validationService.validateCoordinates(destination);
        validationService.validateSourceDirection(source.getDirection());
        int remainingPower;
        int movementX = Math.abs(source.getxCoordinate() - destination.getxCoordinate());
        int movementY = Math.abs(source.getyCoordinate() - destination.getyCoordinate());

        if(isPositionStill(movementX, movementY)){
            return MoveConstants.INITIAL_POWER;
        }

        ArrayList<Character> directions = new ArrayList<>();

        if (movementX == 0) {
            directions.add(source.getyCoordinate() > destination.getyCoordinate() ? Direction.SOUTH.getSymbol() : Direction.NORTH.getSymbol());
            return travelCalculator.travel(movementY, directions, source.getDirection(), Axis.Y);
        }

        if (movementY == 0) {
            directions.add(source.getxCoordinate() > destination.getxCoordinate() ? Direction.WEST.getSymbol() : Direction.EAST.getSymbol());
            return travelCalculator.travel(movementX, directions, source.getDirection(), Axis.X);
        }

        directions.addAll(determineXYDirections(source, destination));
        remainingPower = travelCalculator.travel(movementX + movementY, directions, source.getDirection(), Axis.XY);
        return remainingPower;
    }

    private List<Character> determineXYDirections(GManPositionDTO source, GManPositionDTO destination) {
        List<Character> directions = new ArrayList<>();

        char xDirection = source.getxCoordinate() < destination.getxCoordinate() ? Direction.EAST.getSymbol() : Direction.WEST.getSymbol();
        char yDirection = source.getyCoordinate() < destination.getyCoordinate() ? Direction.NORTH.getSymbol() : Direction.SOUTH.getSymbol();

        directions.add(yDirection);
        directions.add(xDirection);

        return directions;
    }

    private boolean isPositionStill(int movementX, int movementY){
        if (movementX == 0 && movementY == 0) {
            return true;
        }
        return false;
    }
}

