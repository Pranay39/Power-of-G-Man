package com.example.geektrust.service;

import com.example.geektrust.exception.InvalidCoordinatesException;
import com.example.geektrust.exception.InvalidDirectionException;
import com.example.geektrust.model.GManPositionDTO;
import com.example.geektrust.utils.Direction;
import com.example.geektrust.utils.MoveConstants;

import java.util.HashSet;
import java.util.Set;

public class ValidationService {
    private static final Set<Character> VALID_DIRECTIONS = new HashSet<>();

    static {
        VALID_DIRECTIONS.add(Direction.NORTH.getSymbol());
        VALID_DIRECTIONS.add(Direction.SOUTH.getSymbol());
        VALID_DIRECTIONS.add(Direction.EAST.getSymbol());
        VALID_DIRECTIONS.add(Direction.WEST.getSymbol());
    }

    public void validateCoordinates(GManPositionDTO position) throws InvalidCoordinatesException {
        if (position.getxCoordinate() < 0 || position.getxCoordinate() > MoveConstants.GRID_SIZE ||
                position.getyCoordinate() < 0 || position.getyCoordinate() > MoveConstants.GRID_SIZE) {
            throw new InvalidCoordinatesException("Invalid coordinates provided.");
        }
    }

    public void validateSourceDirection(String direction) throws InvalidDirectionException {
        if (!VALID_DIRECTIONS.contains(direction.charAt(0))) {
            throw new InvalidDirectionException("Invalid source direction provided.");
        }
    }
}
