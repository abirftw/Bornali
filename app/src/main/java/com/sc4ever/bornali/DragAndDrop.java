package com.sc4ever.bornali;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;




///ChoiceTouchListener = DragAndDrop
public class DragAndDrop implements View.OnTouchListener {
    private ImageView img;
    private ImageView blankImg;
    private ViewGroup vw;
    private int height;
    private int width;
    private int x1;
    private int y1;
    private int x4;
    private int y4;
    private int xPos;
    private int yPos;
    private int initial_xPos;
    private int initial_yPos;
    private Context Activity;
    private int imgNo;
    public DragAndDrop() {
    }

    public DragAndDrop(Context ctx, ImageView img, ViewGroup v, final ImageView blnkImg, int initial_xPos, int initial_yPos,int imageNO) {

        Activity = ctx;
        vw = v;
        this.img = img;
        this.blankImg = blnkImg;
        this.initial_xPos = initial_xPos;
        this.initial_yPos = initial_yPos;
        blankImg.post(new Runnable(){
            public void run(){
                height = blankImg.getHeight();
                width = blankImg.getWidth();
                ///Diagonals of blankImage.
                x1= (int) blankImg.getX();
                y1 = (int) blankImg.getY();
                x4 = x1 + width;
                y4 = y1 + height + 150;
            }
        });
        this.imgNo = imageNO;

    }

   @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int x = (int) motionEvent.getRawX();
        final int y = (int) motionEvent.getRawY();
        String message1 = String.format("Coordinates of blankIMAGEEEE: (%d %d)",x4,y4);
        Log.d("ans: ",message1);
        String message = String.format("Coordinates of mainimage: (%d %d)",x,y);
        Log.d("ans: ",message);
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {


            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                xPos = x - lParams.leftMargin;
                yPos = y - lParams.topMargin;
                break;


                ///Action_up works when the finger is released.
            case MotionEvent.ACTION_UP:
                if( (x>=x1 && x<=x4) && (y>=y1 && y<=y4) && imgNo == 1)
                {
                    return false;
                }
                else {
                RelativeLayout.LayoutParams layoutParamsInitial = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParamsInitial.leftMargin =initial_xPos;
                layoutParamsInitial.topMargin = initial_yPos;
                view.setLayoutParams(layoutParamsInitial);
                }
                break;


            case MotionEvent.ACTION_POINTER_DOWN:
                break;


            case MotionEvent.ACTION_POINTER_UP:
                break;


            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

                layoutParams.leftMargin = x - xPos;
                layoutParams.topMargin = y - yPos;
                if(layoutParams.leftMargin<-10)
                    layoutParams.leftMargin = -10;
                if(layoutParams.topMargin<-10)
                    layoutParams.topMargin = -10;
                layoutParams.rightMargin = -25;
                layoutParams.bottomMargin = -25;
                view.setLayoutParams(layoutParams);
                break;

        }
        vw.invalidate();
        return true;
    }


}

