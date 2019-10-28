    package com.sc4ever.bornali;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.ArrayList;
    public class PracticeCanvas extends View {
        private float curX = 100;
        private float curY = 100;
        private int width, height;
        private int ballColor ;
        private int index = 0 ; // index of vitalPoints
        private  float lastXCoordinate , lastYCoordinate ;
        private ArrayList<dots> alphabetALine1 = new ArrayList<dots>();
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


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(20);
            width = canvas.getWidth() / 10;
            height = canvas.getHeight() / 10;

            canvas.drawCircle(curX, curY, 20, paint); // make the dot initially visible
            //canvas.drawLine(2*width, 7*height ,5*width,2*height,paint);
            //canvas.drawLine(5*width,2*height,8*width,7*height,paint);

            fillListWithDots(); // prepare arrayList to construct the alphabets

            for (dots i : alphabetALine1) {
                canvas.drawCircle(i.getCoordinateX(), i.getCoordinateY(), 15, paint);
            }

            lastXCoordinate = vitalPoints.get(index).getCoordinateX() ;
            lastYCoordinate = vitalPoints.get(index).getCoordinateY() ;

            for (dots i : pathList){
                canvas.drawLine(lastXCoordinate,lastYCoordinate , i.getCoordinateX(),i.getCoordinateY(),paint);
                index++ ;
            }

        }


        private void fillListWithDots() {
            float j ;
            for (float i = 2 * width; i <= 8 * width; i += 50) {
                if(i <= 5*width) {
                    j = height * 10 - i * 2361 / 1000;
                    if (i == 2 * width || i >= 5 * width - 49)
                        vitalPoints.add(new dots(i, j));
                }
                else {
                    j = i * 2361 / 1000 - 9072/10;
                    if (i >= 8 * width - 49)
                        vitalPoints.add(new dots(i, j));
                }

                alphabetALine1.add(new dots(i, j));
            }
            for(float i = 35/10*width ; i <= 65/10*width ; i+=80){
                    j = 78366 / 100;
                    alphabetALine1.add(new dots(i, j));
            }
        }

        public void updatePath(float x , float y){ // update The pathList
            pathList.add(new dots(x,y)) ;
        }

        public void print(){
            System.out.println(vitalPoints.get(0).getCoordinateX()+" vital0 " + vitalPoints.get(0).getCoordinateY());
            System.out.println(lastXCoordinate + " last " + lastYCoordinate);
            System.out.println(alphabetALine1.get(2).getCoordinateX() + " huhu2 " + alphabetALine1.get(2).getCoordinateY()) ;
            System.out.println(alphabetALine1.get(10).getCoordinateX() + " huhu10 " + alphabetALine1.get(10).getCoordinateY()) ;
            if(pathList.size() > 0)
                System.out.println(pathList.get(0).getCoordinateX() + " pathList " + pathList.get(0).getCoordinateY());
        }

    }
