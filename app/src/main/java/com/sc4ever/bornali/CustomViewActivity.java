package com.sc4ever.bornali ;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_acitvity);

        listener();

    }



    private void listener(){
        RelativeLayout rootLayout = findViewById(R.id.practiceCanvas);
        final PracticeCanvas practiceCanvas = new PracticeCanvas(this);
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            int index = 0 ;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                practiceCanvas.setCurX(motionEvent.getX());
                practiceCanvas.setCurY(motionEvent.getY());
                for(int i = index ; i < practiceCanvas.vitalPoints.size() ; i++){
                    float x = practiceCanvas.vitalPoints.get(i).getCoordinateX(), y = practiceCanvas.vitalPoints.get(i).getCoordinateY();
                    if(compare(x,y,motionEvent.getX(),motionEvent.getY())) {
                        practiceCanvas.updatePath(x, y);
                        index++ ;
                    }
                }
                practiceCanvas.setBallColor(Color.GREEN);
                practiceCanvas.print();
                practiceCanvas.invalidate();
                return true;
            }
        };
        practiceCanvas.setOnTouchListener(onTouchListener);
        rootLayout.addView(practiceCanvas);
    }

    private boolean compare (float x1 , float y1 , float x2 , float y2) {
        return  (x2 >= x1 - 5 && x2 <= x1 + 5 && y2 >= y1 - 5 && y2 <= y1 + 5 );
    }
}