    package com.sc4ever.bornali;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.ArrayList;
import java.util.List;

    public class PracticeCanvas extends View {
    private float curX = 100 ;
    private float cury = 100 ;
    private float dotX , dotY ;
    private int ballColor = Color.GREEN ;
    public int getBallColor(){
        return ballColor ;
    }
    public void setBallColor(int ballColor){
        this.ballColor = ballColor ;
    }

    public float getCurX() {
        return curX;
    }

    public void setCurX(float curX) {
        this.curX = curX;
    }

    public float getCury() {
        return cury;
    }

    public void setCury(float cury) {
        this.cury = cury;
    }

    public PracticeCanvas(Context context) {
        super(context);
    }
    private List<dots> alphabets = new ArrayList<>();
    public void constructAlphabet(){
        alphabets.add(new dots(100,400));
        alphabets.add(new dots(90 , 360));
        alphabets.add(new dots(80,320));
        alphabets.add(new dots(70,280));
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(this.getBallColor());
        canvas.drawCircle(curX,cury,35,paint);

        canvas.drawCircle(180,900,20,paint);
        canvas.drawCircle(220,780,20,paint);
        canvas.drawCircle(290,600,20,paint);
        canvas.drawCircle(360,400,20,paint);
        canvas.drawCircle(460,200,20,paint);
        canvas.drawCircle(540,400,20,paint);
        canvas.drawCircle(610,600,20,paint);
        canvas.drawCircle(410,600,20,paint);
        canvas.drawCircle(500,600,20,paint);
        canvas.drawCircle(680,780,20,paint);
        canvas.drawCircle(730,900,20,paint);
    }
}
