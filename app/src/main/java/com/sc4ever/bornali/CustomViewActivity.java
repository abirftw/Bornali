package com.sc4ever.bornali ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_acitvity);

        listener();

        Button HomePageBtn = findViewById(R.id.GoTohomePageBtn) ;
        HomePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomViewActivity.this, HomePageActivity.class) ;
                startActivity(intent);
            }
        });

    }


    private  void buttonActivity(){

    }
    private void listener(){
        RelativeLayout rootLayout = findViewById(R.id.practiceCanvas);
        final Alphabets currentAlphabet = new Alphabets() ;
        final PracticeCanvas practiceCanvas = new PracticeCanvas(this);


        Button next = (Button) findViewById(R.id.customDrawNextBtn) ;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                practiceCanvas.pathList.clear();
                practiceCanvas.invalidate();
                practiceCanvas.updatePath(practiceCanvas.alphabetALine1.get(0).getCoordinateX(),practiceCanvas.alphabetALine1.get(0).getCoordinateY());
            }
        });
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            int index = 0 ;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                practiceCanvas.setCurX(motionEvent.getX());
                practiceCanvas.setCurY(motionEvent.getY());
                for(int i = index ; i < practiceCanvas.alphabetALine1.size() ; i++){
                    float x = practiceCanvas.alphabetALine1.get(i).getCoordinateX(), y = practiceCanvas.alphabetALine1.get(i).getCoordinateY();
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
        return  (x2 >= x1 - 15 && x2 <= x1 + 15 && y2 >= y1 - 15 && y2 <= y1 + 15 );
    }


}