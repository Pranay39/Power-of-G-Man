package com.example.geektrust.service;

import com.example.geektrust.utils.Axis;
import java.util.List;
public class YPowerCalculatorService extends PowerCalculatorBase  {
    @Override
    public int calculatePower(int movements, List<Character> directions, String sourceDirection) {
        return calculatePowerCommon(movements, directions, sourceDirection, Axis.Y);
    }
}

