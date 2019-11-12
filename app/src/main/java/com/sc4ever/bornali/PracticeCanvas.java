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
    public ArrayList<dots> alphabetALine1 = new ArrayList<dots>();
    public ArrayList <dots> pathList = new ArrayList<dots>() ;
    public ArrayList<dots> vitalPoints = new ArrayList<dots>() ;

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

        fillListWithDots(); // prepare arrayList to construct the alphabets

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


    private void fillListWithDots() {
        alphabetALine1.add(new dots(7*width , (float)24/10*height)) ;
        alphabetALine1.add(new dots(6*width , (float)21/10*height)) ;
        alphabetALine1.add(new dots(5*width , 2*height)) ;
        alphabetALine1.add(new dots(4*width , (float)22/10*height)) ;
        alphabetALine1.add(new dots(3*width , (float)25/10*height)) ;

        alphabetALine1.add(new dots((float)25/10*width , (float)28/10*height)) ;
        alphabetALine1.add(new dots((float)22/10*width , (float)31/10*height)) ;
        alphabetALine1.add(new dots(2*width , (float)35/10*height)) ;
        alphabetALine1.add(new dots((float)19/10*width , 4*height)) ;
        alphabetALine1.add(new dots((float)19/10*width , (float)45/10*height)) ;
        alphabetALine1.add(new dots((float)19/10*width , 5*height)) ;

        alphabetALine1.add(new dots((float)21/10*width , (float)55/10*height)) ;
        alphabetALine1.add(new dots((float)25/10*width , 6*height)) ;
        alphabetALine1.add(new dots(3*width , (float)63/10*height)) ;
        alphabetALine1.add(new dots(4*width , (float)66/10*height)) ;
        alphabetALine1.add(new dots(5*width , (float)68/10*height)) ;
        alphabetALine1.add(new dots(6*width , (float)68/10*height)) ;
        alphabetALine1.add(new dots(7*width , (float)65/10*height)) ;

    }

    public void updatePath(float x , float y){ // update The pathList
        pathList.add(new dots(x,y)) ;
    }

    public void print(){
        System.out.println(alphabetALine1.get(2).getCoordinateX() + " " + alphabetALine1.get(2).getCoordinateY()) ;
        System.out.println(getScreenWidth()+ " screen " + getScreenHeight());
    }

}