package com.sc4ever.bornali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;

import static com.sc4ever.bornali.ConvertCoordinate.dpToPx;

public class LevelOne extends AppCompatActivity {
    Button buttonBacktoMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

        ImageView blankImg = (ImageView)  findViewById(R.id.blankbirdmain);
        blankImg.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int x = dpToPx(event.getX());
                int y = dpToPx(event.getY());

                String message = String.format("Coordinates: (%d %d)",x,y);
                Log.d("ans: ",message);
                return false;
            }
        });


        ViewGroup vv = (ViewGroup) findViewById(R.id.levelOnePage);
        ImageView img = (ImageView)  vv.findViewById(R.id.mainbird);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ConvertCoordinate.dpToPx(144),ConvertCoordinate.dpToPx(144));
        layoutParams.leftMargin = dpToPx(150);
        layoutParams.topMargin = dpToPx(300);
        img.setLayoutParams(layoutParams);


         img.setOnTouchListener(new DragAndDrop(getApplicationContext(), img,vv,blankImg,layoutParams.leftMargin,layoutParams.topMargin));

        buttonBacktoMenu = (Button) findViewById(R.id.backbuttontoMenu);
        buttonBacktoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        });
    }

    void Back() {
        System.out.println("buttonBacktoMenu");
        Intent intentBackk = new Intent(this, PuzzleMenuPage.class);
        startActivity(intentBackk);
    }

}