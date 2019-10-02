package com.sc4ever.bornali;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;




///ChoiceTouchListener = DragAndDrop
public class DragAndDrop implements View.OnTouchListener {
    private ImageView img;
    private ViewGroup vw;
    private int xPos;
    private int yPos;
    private Context Activity;

    public DragAndDrop() {
    }

    public DragAndDrop(Context ctx, ImageView im, ViewGroup v) {

        Activity = ctx;
        img = im;
        vw = v;

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        img.setLayoutParams(layoutParams);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int x = (int) motionEvent.getRawX();
        final int y = (int) motionEvent.getRawY();

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                xPos = x - lParams.leftMargin;
                yPos = y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = x - xPos;
                layoutParams.topMargin = y - yPos;
                layoutParams.rightMargin = -25;
                layoutParams.bottomMargin = -25;
                view.setLayoutParams(layoutParams);
                break;

        }
        vw.invalidate();
        return true;
    }


}

