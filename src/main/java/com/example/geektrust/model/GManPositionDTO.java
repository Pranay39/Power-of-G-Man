package com.example.geektrust.model;

public class GManPositionDTO {
    private int xCoordinate;
    private int yCoordinate;
    private String direction;

    public GManPositionDTO(int xCoordinate, int yCoordinate, String direction) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.direction = direction;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
