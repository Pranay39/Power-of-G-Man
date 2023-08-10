package com.example.geektrust.service;

import com.example.geektrust.utils.Axis;

import java.util.List;

public class TravelCalculator {
    public int travel(int movements, List<Character> directions, String sourceDirection, Axis axis) {
        PowerCalculator calculator;

        if (axis.equals(Axis.Y)) {
            calculator = new YPowerCalculator();
        } else if (axis.equals(Axis.X)) {
            calculator = new XPowerCalculator();
        } else if (axis.equals(Axis.XY)) {
            calculator = new XYPowerCalculator();
        } else {
            throw new UnsupportedOperationException("Invalid axis provided.");
        }

        return calculator.calculatePower(movements, directions, sourceDirection);
    }
}
