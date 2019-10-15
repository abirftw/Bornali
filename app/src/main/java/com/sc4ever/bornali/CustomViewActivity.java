package com.sc4ever.bornali ;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_acitvity);
        LinearLayout rootLayout = (LinearLayout)findViewById(R.id.practiceCanvas);
        final PracticeCanvas practiceCanvas = new PracticeCanvas(this);
        practiceCanvas.setMinimumWidth(500);
        practiceCanvas.setMinimumHeight(800);

        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                practiceCanvas.setCurX(motionEvent.getX());
                practiceCanvas.setCury(motionEvent.getY());
                practiceCanvas.setBallColor(Color.GREEN);
                practiceCanvas.invalidate();
                return true;
            }
        };
        practiceCanvas.setOnTouchListener(onTouchListener);
        rootLayout.addView(practiceCanvas);
    }
}