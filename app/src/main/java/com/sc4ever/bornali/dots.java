package com.sc4ever.bornali;

public class dots {
    private float coordinateX , CoordinateY ;

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public float getCoordinateY() {
        return CoordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        CoordinateY = coordinateY;
    }
    public dots(float x , float y){
        this.coordinateX = x ;
        this.CoordinateY = y ;
    }
}
