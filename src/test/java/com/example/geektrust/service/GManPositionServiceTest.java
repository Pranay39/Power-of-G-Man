package com.example.geektrust.service;

import com.example.geektrust.exception.InvalidCoordinatesException;
import com.example.geektrust.exception.InvalidDirectionException;
import com.example.geektrust.model.GManPositionDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GManPositionServiceTest {

    @Test
    public void testSamePosition() {
        GManPositionDTO source = new GManPositionDTO(0, 0, "N");
        GManPositionDTO destination = new GManPositionDTO(0, 0, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(200, result, "Expected 200 power when source and destination are the same.");
    }

    @Test
    public void testXAxisMovementWithNorth() {
        GManPositionDTO source = new GManPositionDTO(0, 0, "N");
        GManPositionDTO destination = new GManPositionDTO(3, 0, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(165, result, "Expected remaining power for X-axis movement.");
    }

    @Test
    public void testXAxisMovementWithSouth() {
        GManPositionDTO source = new GManPositionDTO(0, 0, "S");
        GManPositionDTO destination = new GManPositionDTO(3, 0, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(165, result, "Expected remaining power for X-axis movement.");
    }

    @Test
    public void testXAxisMovementWithWest() {
        GManPositionDTO source = new GManPositionDTO(0, 0, "W");
        GManPositionDTO destination = new GManPositionDTO(3, 0, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(160, result, "Expected remaining power for X-axis movement.");
    }

    @Test
    public void testXAxisMovementWithEast() {
        GManPositionDTO source = new GManPositionDTO(0, 0, "E");
        GManPositionDTO destination = new GManPositionDTO(3, 0, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(170, result, "Expected remaining power for X-axis movement.");
    }

    @Test
    public void testYAxisMovementWithNorth() {
        GManPositionDTO source = new GManPositionDTO(0, 0, "N");
        GManPositionDTO destination = new GManPositionDTO(0, 4, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(160, result, "Expected remaining power for Y-axis movement.");
    }

    @Test
    public void testYAxisMovementWithSouth() {
        GManPositionDTO source = new GManPositionDTO(0, 0, "S");
        GManPositionDTO destination = new GManPositionDTO(0, 4, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(150, result, "Expected remaining power for Y-axis movement.");
    }

    @Test
    public void testYAxisMovementWithWest() {
        GManPositionDTO source = new GManPositionDTO(0, 0, "W");
        GManPositionDTO destination = new GManPositionDTO(0, 4, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(155, result, "Expected remaining power for Y-axis movement.");
    }

    @Test
    public void testYAxisMovementWithEast() {
        GManPositionDTO source = new GManPositionDTO(0, 0, "E");
        GManPositionDTO destination = new GManPositionDTO(0, 4, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(155, result, "Expected remaining power for Y-axis movement.");
    }

    @Test
    public void testMovementToGridEdge() {
        GManPositionDTO source = new GManPositionDTO(3, 3, "N");
        GManPositionDTO destination = new GManPositionDTO(6, 3, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(165, result, "Expected remaining power for movement to grid edge.");
    }

    @Test
    public void testMovementToGridCorner() {
        GManPositionDTO source = new GManPositionDTO(3, 3, "S");
        GManPositionDTO destination = new GManPositionDTO(0, 6, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(130, result, "Expected remaining power for movement to grid corner.");
    }

    @Test
    public void testXAxisMovementWithinGrid() {
        GManPositionDTO source = new GManPositionDTO(0, 0, "W");
        GManPositionDTO destination = new GManPositionDTO(6, 0, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(130, result, "Expected remaining power for maximum X-axis movement within the grid.");
    }

    @Test
    public void testYAxisMovementWithinGrid() {
        GManPositionDTO source = new GManPositionDTO(3, 0, "S");
        GManPositionDTO destination = new GManPositionDTO(3, 6, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(130, result, "Expected remaining power for maximum Y-axis movement within the grid.");
    }

    @Test
    public void testDiagonalCoordinates() {
        GManPositionDTO source = new GManPositionDTO(0, 0, "W");
        GManPositionDTO destination = new GManPositionDTO(6, 6, "");

        int result = GManPositionService.calculateTurnsAndSteps(source, destination);

        Assertions.assertEquals(70, result, "Expected remaining power for diagonal movement within the grid.");
    }


    @Test
    public void testInvalidSourceOrientation() {
        GManPositionDTO source = new GManPositionDTO(3, 3, "Invalid");
        GManPositionDTO destination = new GManPositionDTO(4, 3, "");

        // Since the source orientation is invalid, the method should throw an exception or return a specific error code/handle the invalid input.
        Assertions.assertThrows(InvalidDirectionException.class, () -> GManPositionService.calculateTurnsAndSteps(source, destination));
    }

    @Test
    public void testInvalidCoordinatesOutsideGrid() {
        GManPositionDTO source = new GManPositionDTO(9, 0, "E");
        GManPositionDTO destination = new GManPositionDTO(3, 3, "");

        // Since the destination is outside the grid limits, the method should throw an exception or return a specific error code/handle the invalid input.
        Assertions.assertThrows(InvalidCoordinatesException.class, () -> GManPositionService.calculateTurnsAndSteps(source, destination));
    }

    @Test
    public void testNullSourceOrDestination() {
        GManPositionDTO source = null;
        GManPositionDTO destination = new GManPositionDTO(4, 3, "W");

        // Since the source is null, the method should throw an exception or return a specific error code/handle the invalid input.
        Assertions.assertThrows(NullPointerException.class, () -> GManPositionService.calculateTurnsAndSteps(source, destination));
    }


}
