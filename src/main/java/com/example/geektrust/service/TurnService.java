package com.example.geektrust.service;

import com.example.geektrust.utils.Axis;
import com.example.geektrust.utils.MoveConstants;

import java.util.*;

public interface TurnService extends PowerCalculatorService {
    default int calculateTurnCost(Axis axis, String sourceDirection) {
        Map<Axis, Set<Character>> allowedSymbols = new HashMap<>();
        allowedSymbols.put(Axis.X, new HashSet<>(Arrays.asList('N', 'S')));
        allowedSymbols.put(Axis.Y, new HashSet<>(Arrays.asList('E', 'W')));

        char directionSymbol = sourceDirection.charAt(0);

        if (allowedSymbols.containsKey(axis) && allowedSymbols.get(axis).contains(directionSymbol)) {
            return MoveConstants.TURN_COST;
        } else {
            return MoveConstants.TURN_COST * MoveConstants.TWO_TURNS;
        }
    }
}

