package com.sc4ever.bornali;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


import java.util.ArrayList;
public class PracticeCanvas extends View {
    private float curX = 100;
    private float curY = 100;
    private int width = getScreenWidth()/10 ;
    private int height = getScreenHeight()/10 ;
    private int ballColor ;
    private int index = 0 ; // index of vitalPoints
    private  float lastXCoordinate , lastYCoordinate ;
    public ArrayList<dots> alphabetALine1 = new ArrayList();
    public ArrayList <dots> pathList = new ArrayList<dots>() ;

    public void setCurX(float curX) {
        this.curX = curX;
    }
    public void setBallColor(int color){
        this.ballColor = color ;
    }
    public void setCurY(float cury) {
        this.curY = cury;
    }

    public PracticeCanvas(Context context) {
        super(context);
    }

    private static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels ;
    }
    private static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(20);

        canvas.drawCircle(curX, curY, 20, paint);  // a pointer is drawn on the canvas which will move corresponding to the touch on screen


        updateAlphabetList();
        for (dots j : alphabetALine1) { // draw the alphabet layout
            canvas.drawCircle(j.getCoordinateX(), j.getCoordinateY(), 15, paint);
        }

        //set the last coordinates as the 0th element in the array
        lastXCoordinate = alphabetALine1.get(0).getCoordinateX() ;
        lastYCoordinate = alphabetALine1.get(0).getCoordinateY() ;

        for (dots i : pathList) {
            canvas.drawLine(lastXCoordinate,lastYCoordinate , i.getCoordinateX(),i.getCoordinateY(),paint);
            lastXCoordinate = i.getCoordinateX();
            lastYCoordinate = i.getCoordinateY();
        }
    }


    private void updateAlphabetList() {
        float j;
        float slope = (6 * height) / (3 * width);
        float constant = (8 * width * slope / 10) - (7 * height / 10);
        for (float i = 2 * width; i <= 8 * width; i += width / 2) {
            if (i <= 5 * width) {
                j = height * 10 - i * slope;
            } else {
                j = i * slope - constant * 21 / 2;
            }
            alphabetALine1.add(new dots(i, j));
        }
        for (float i = 35 / 10 * width; i <= 65 / 10 * width; i += width) {
            j = 632;
            alphabetALine1.add(new dots(i, j));
        }
    }
    public void changeAlphabetList(ArrayList<dots> newList){
        alphabetALine1.clear();
        alphabetALine1 = newList ;
    }

    public void updatePath(float x , float y){ // update The pathList
        pathList.add(new dots(x,y)) ;
    }

    public void print(){
        System.out.println("PathList Size : " + pathList.size()) ;
    }

}