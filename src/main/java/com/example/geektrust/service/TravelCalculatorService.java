package com.example.geektrust.service;

import com.example.geektrust.utils.Axis;

import java.util.List;

public class TravelCalculatorService {
    public int travel(int movements, List<Character> directions, String sourceDirection, Axis axis) {
        PowerCalculatorService calculator;

        if (axis.equals(Axis.Y)) {
            calculator = new YPowerCalculatorService();
        } else if (axis.equals(Axis.X)) {
            calculator = new XPowerCalculatorService();
        } else if (axis.equals(Axis.XY)) {
            calculator = new XYPowerCalculatorService();
        } else {
            throw new UnsupportedOperationException("Invalid axis provided.");
        }

        return calculator.calculatePower(movements, directions, sourceDirection);
    }
}
