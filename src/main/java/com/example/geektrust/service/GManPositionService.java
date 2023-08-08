package com.example.geektrust.service;

import com.example.geektrust.exception.InvalidCoordinatesException;
import com.example.geektrust.exception.InvalidDirectionException;
import com.example.geektrust.model.GManPositionDTO;
import com.example.geektrust.utils.Axis;
import com.example.geektrust.utils.Direction;
import com.example.geektrust.utils.MoveConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.geektrust.service.GManTravelService.travel;

public class GManPositionService {


    private static final Set<Character> VALID_DIRECTIONS = new HashSet<>();

    static {
        VALID_DIRECTIONS.add(Direction.NORTH.getSymbol());
        VALID_DIRECTIONS.add(Direction.SOUTH.getSymbol());
        VALID_DIRECTIONS.add(Direction.EAST.getSymbol());
        VALID_DIRECTIONS.add(Direction.WEST.getSymbol());
    }

    /**
     * Calculate the number of turns and steps needed for the G-Man to move from the source position to the destination position on the grid.
     *
     * @param source      The source position of the G-Man.
     * @param destination The destination position of the G-Man.
     * @return The remaining power after the travel.
     * @throws InvalidCoordinatesException If the provided coordinates are invalid.
     */
    public static int calculateTurnsAndSteps(GManPositionDTO source, GManPositionDTO destination) throws InvalidCoordinatesException {
        validateCoordinates(source);
        validateCoordinates(destination);
        validateSourceDirection(source.getDirection());

        int movementX = Math.abs(source.getxCoordinate() - destination.getxCoordinate());
        int movementY = Math.abs(source.getyCoordinate() - destination.getyCoordinate());

        if (movementX == 0 && movementY == 0) {
            return MoveConstants.INITIAL_POWER;
        }

        ArrayList<Character> directions = new ArrayList<>();
        int remainingPower;

        if (movementX == 0) {
            directions.add(source.getyCoordinate() > destination.getyCoordinate() ? Direction.SOUTH.getSymbol() : Direction.NORTH.getSymbol());
            return travel(movementY, directions, source.getDirection(), Axis.Y);
        }

        if (movementY == 0) {
            directions.add(source.getxCoordinate() > destination.getxCoordinate() ? Direction.WEST.getSymbol() : Direction.EAST.getSymbol());
            return travel(movementX, directions, source.getDirection(), Axis.X);
        }

        directions.addAll(determineXYDirections(source, destination));
        remainingPower = travel(movementX + movementY, directions, source.getDirection(), Axis.XY);
        return remainingPower;
    }

    private static List<Character> determineXYDirections(GManPositionDTO source, GManPositionDTO destination) {
        List<Character> directions = new ArrayList<>();

        char xDirection = source.getxCoordinate() < destination.getxCoordinate() ? Direction.EAST.getSymbol() : Direction.WEST.getSymbol();
        char yDirection = source.getyCoordinate() < destination.getyCoordinate() ? Direction.NORTH.getSymbol() : Direction.SOUTH.getSymbol();

        directions.add(yDirection);
        directions.add(xDirection);

        return directions;
    }

    private static void validateCoordinates(GManPositionDTO position) throws InvalidCoordinatesException {
        if (position.getxCoordinate() < 0 || position.getxCoordinate() > MoveConstants.GRID_SIZE ||
                position.getyCoordinate() < 0 || position.getyCoordinate() > MoveConstants.GRID_SIZE) {
            throw new InvalidCoordinatesException("Invalid coordinates provided.");
        }
    }

    private static void validateSourceDirection(String direction) throws InvalidDirectionException {
        if (!VALID_DIRECTIONS.contains(direction.charAt(0))) {
            throw new InvalidDirectionException("Invalid source direction provided.");
        }
    }
}

//package com.example.geektrust.service;
//
//import com.example.geektrust.exception.InvalidCoordinatesException;
//import com.example.geektrust.exception.InvalidDirectionException;
//import com.example.geektrust.model.GManPositionDTO;
//import com.example.geektrust.utils.Axis;
//import com.example.geektrust.utils.Direction;
//import com.example.geektrust.utils.MoveConstants;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static com.example.geektrust.service.GManTravelService.travel;
//
//public class GManPositionService {
//
//    private static final Set<Character> VALID_DIRECTIONS = new HashSet<>();
//    static {
//        VALID_DIRECTIONS.add(Direction.NORTH.getSymbol());
//        VALID_DIRECTIONS.add(Direction.SOUTH.getSymbol());
//        VALID_DIRECTIONS.add(Direction.EAST.getSymbol());
//        VALID_DIRECTIONS.add(Direction.WEST.getSymbol());
//    }
//
//    public static int calculateTurnsAndSteps(GManPositionDTO source, GManPositionDTO destination)
//            throws InvalidCoordinatesException, InvalidDirectionException {
//        validateCoordinates(source);
//        validateCoordinates(destination);
//        validateSourceDirection(source.getDirection());
//
//        int movementX = Math.abs(source.getxCoordinate() - destination.getxCoordinate());
//        int movementY = Math.abs(source.getyCoordinate() - destination.getyCoordinate());
//
//        if (source.getxCoordinate() == destination.getxCoordinate() && source.getyCoordinate() == destination.getyCoordinate()) {
//            return MoveConstants.INITIAL_POWER;
//        }
//
//        ArrayList<Character> directions = new ArrayList<>();
//        int remainingPower;
//
//        if (source.getxCoordinate() == destination.getxCoordinate()) {
//            directions.add(source.getyCoordinate() > destination.getyCoordinate() ? Direction.SOUTH.getSymbol() : Direction.NORTH.getSymbol());
//            return travel(movementY, directions, source.getDirection(), Axis.Y);
//        } else if (source.getyCoordinate() == destination.getyCoordinate()) {
//            directions.add(source.getxCoordinate() > destination.getxCoordinate() ? Direction.WEST.getSymbol() : Direction.EAST.getSymbol());
//            return travel(movementX, directions, source.getDirection(), Axis.X);
//        } else {
//            directions.addAll(determineXYDirections(source, destination));
//            remainingPower = travel(movementX + movementY, directions, source.getDirection(), Axis.XY);
//            return remainingPower;
//        }
//    }
//
//
//
//    private static void validateCoordinates(GManPositionDTO position) throws InvalidCoordinatesException {
//        if (position.getxCoordinate() < 0 || position.getxCoordinate() > MoveConstants.GRID_SIZE ||
//                position.getyCoordinate() < 0 || position.getyCoordinate() > MoveConstants.GRID_SIZE) {
//            throw new InvalidCoordinatesException("Invalid coordinates provided.");
//        }
//    }
//
//    private static void validateSourceDirection(String direction) throws InvalidDirectionException {
//        if (!VALID_DIRECTIONS.contains(direction.charAt(0))) {
//            throw new InvalidDirectionException("Invalid source direction provided.");
//        }
//    }
//
//    private static List<Character> determineXYDirections(GManPositionDTO source, GManPositionDTO destination) {
//        List<Character> directions = new ArrayList<>();
//        if (source.getxCoordinate() < destination.getxCoordinate()) {
//            directions.add(source.getyCoordinate() < destination.getyCoordinate() ? Direction.NORTH.getSymbol() : Direction.SOUTH.getSymbol());
//            directions.add(Direction.EAST.getSymbol());
//        } else {
//            directions.add(source.getyCoordinate() > destination.getyCoordinate() ? Direction.SOUTH.getSymbol() : Direction.NORTH.getSymbol());
//            directions.add(Direction.WEST.getSymbol());
//        }
//        return directions;
//    }
//
//
//}


//        // Throw exception if the co-ordinates are invalid
//        if (!isValidCoordinates(source) || !isValidCoordinates(destination)) {
//            throw new InvalidCoordinatesException("Invalid coordinates provided.");
//        }
//        // Throw exception if source direction provided is invalid
//          if(!VALID_DIRECTIONS.contains(source.getDirection().charAt(0))){
//            throw new InvalidDirectionException("Invalid source direction provided.");
//        }

//    private static List<Character> determineXYDirections(GManPositionDTO source, GManPositionDTO destination){
//        List<Character> directions = new ArrayList<>();
//        if (source.getxCoordinate() < destination.getxCoordinate()) {
//            if (source.getyCoordinate() < destination.getyCoordinate()) {
//                directions.add(Direction.NORTH.getSymbol());
//                directions.add(Direction.EAST.getSymbol());
//            } else {
//                directions.add(Direction.SOUTH.getSymbol());
//                directions.add(Direction.EAST.getSymbol());
//            }
//        }
//        if (source.getxCoordinate() > destination.getxCoordinate()) {
//            if (source.getyCoordinate() > destination.getyCoordinate()) {
//                directions.add(Direction.SOUTH.getSymbol());
//                directions.add(Direction.WEST.getSymbol());
//            } else {
//                directions.add(Direction.NORTH.getSymbol());
//                directions.add(Direction.WEST.getSymbol());
//            }
//        }
//        return directions;
//    }


//    public static int calculateTurnsAndSteps(GManPositionDTO source, GManPositionDTO destination) throws InvalidCoordinatesException {
//
//        validateCoordinates(source);
//        validateCoordinates(destination);
//        validateSourceDirection(source.getDirection());
//
//
//        int movementX = Math.abs(source.getxCoordinate() - destination.getxCoordinate());
//        int movementY = Math.abs(source.getyCoordinate() - destination.getyCoordinate());
//
//        // If the source and destination co-ordinates are same that means the G-Man does not need to move, so no power utilised
//        if (source.getxCoordinate() == destination.getxCoordinate() && source.getyCoordinate() == destination.getyCoordinate()) {
//            return MoveConstants.INITIAL_POWER;
//        }
//
//        ArrayList<Character> directions = new ArrayList<>();
//        int remainingPower;
//
//        if (source.getxCoordinate() == destination.getxCoordinate()) {
//            if (source.getyCoordinate() > destination.getyCoordinate()) {
//                directions.add(Direction.SOUTH.getSymbol());
//                return travel(movementY, directions, source.getDirection(), Axis.Y);
//            } else {
//                directions.add(Direction.NORTH.getSymbol());
//                return travel(movementY, directions, source.getDirection(), Axis.Y);
//            }
//        }
//
//        if (source.getyCoordinate() == destination.getyCoordinate()) {
//            if (source.getxCoordinate() > destination.getxCoordinate()) {
//                directions.add(Direction.WEST.getSymbol());
//                return travel(movementX, directions, source.getDirection(), Axis.X);
//            } else {
//                directions.add(Direction.EAST.getSymbol());
//                return travel(movementX, directions, source.getDirection(), Axis.X);
//
//            }
//        } else {
//            directions.addAll(determineXYDirections(source, destination));
//            remainingPower = travel(movementX + movementY, directions, source.getDirection(), Axis.XY);
//            return remainingPower;
//        }
//    }
