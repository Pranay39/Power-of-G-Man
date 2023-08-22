package com.example.geektrust.service;

import com.example.geektrust.utils.Axis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravelCalculatorService {

    public int travel(int movements, List<Character> directions, String sourceDirection, Axis axis) {
        Map<Axis, PowerCalculatorService> calculatorMap = new HashMap<>();
        calculatorMap.put(Axis.Y, new YPowerCalculatorService());
        calculatorMap.put(Axis.X, new XPowerCalculatorService());
        calculatorMap.put(Axis.XY, new XYPowerCalculatorService());

        PowerCalculatorService calculator = calculatorMap.getOrDefault(axis, null);

        if (calculator == null) {
            throw new UnsupportedOperationException("Invalid axis provided.");
        }

        return calculator.calculatePower(movements, directions, sourceDirection);
    }

}
