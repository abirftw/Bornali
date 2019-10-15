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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;

import static com.sc4ever.bornali.ConvertCoordinate.dpToPx;

public class LevelOne extends AppCompatActivity {
    private Button buttonBacktoMenu;
    private ImageView blankImg;
    private ImageView img;
    private ImageView levelWinImg;
    private TextView levelWinText;
    private ViewGroup vv;
    private RelativeLayout.LayoutParams layoutParams;
    private Animation textAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

        blankImg = (ImageView)  findViewById(R.id.blankbirdmain);
        blankImg.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int x = dpToPx(event.getX());
                int y = dpToPx(event.getY());

                String message = String.format("Coordinates: (%d %d)",x,y);
                Log.d("ans: ",message);
                return false;
            }
        });


        vv = (ViewGroup) findViewById(R.id.levelOnePage);
        img = (ImageView)  vv.findViewById(R.id.mainbird);

         layoutParams = new RelativeLayout.LayoutParams(ConvertCoordinate.dpToPx(144),ConvertCoordinate.dpToPx(144));
        layoutParams.leftMargin = dpToPx(150);
        layoutParams.topMargin = dpToPx(300);
        img.setLayoutParams(layoutParams);

        levelWinImg = (ImageView) findViewById(R.id.levelwinimage);
        levelWinText = (TextView) findViewById(R.id.levelwintext);
        img.setOnTouchListener(new View.OnTouchListener() {
            DragAndDrop dragAndDrop = new DragAndDrop(getApplicationContext(), img,vv,blankImg,layoutParams.leftMargin,layoutParams.topMargin);
            public boolean onTouch(View v, MotionEvent event) {

                boolean puzzleState = dragAndDrop.onTouch(v, event);
                if(puzzleState == true)
                return true;
                if(puzzleState == false)
                {
                    blankImg.setImageResource(R.drawable.levelonebird);
                    img.setImageDrawable(null);
                    levelWinImg.setImageResource(R.drawable.levelwin);
                    textAnim = AnimationUtils.loadAnimation(LevelOne.this,R.anim.levelwinanimtext);
                    levelWinText.setText("Great!");
                    levelWinText.setAnimation(textAnim);
                    return false;
                }

                return true;
            }

        });

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