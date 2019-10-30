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

public class LevelOneEasy extends AppCompatActivity {
    private Button buttonBacktoMenu;
    private Button buttonNextLevel;
    private ImageView blankImg;
    private ImageView img,img1;
    private ImageView levelWinImg;
    private TextView levelWinText;
    private ViewGroup vv,vv1;
    private RelativeLayout.LayoutParams layoutParams,layoutParams1;
    private Animation Anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one_easy);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        blankImg = (ImageView)  findViewById(R.id.blankTreeLevelOneEasy);
        blankImg.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int x = dpToPx(event.getX());
                int y = dpToPx(event.getY());

                String message = String.format("Coordinates: (%d %d)",x,y);
                Log.d("ans: ",message);
                return false;
            }
        });


        vv = (ViewGroup) findViewById(R.id.levelOneEasyPage);
        img = (ImageView)  vv.findViewById(R.id.mainTreeLevelOneEasy);

        layoutParams = new RelativeLayout.LayoutParams(ConvertCoordinate.dpToPx(144),ConvertCoordinate.dpToPx(144));
        layoutParams.leftMargin = dpToPx(25);
        layoutParams.topMargin = dpToPx(300);
        img.setLayoutParams(layoutParams);

        levelWinImg = (ImageView) findViewById(R.id.leveloneeasywinimage);
        levelWinText = (TextView) findViewById(R.id.leveloneeasywintext);
        buttonNextLevel = (Button) findViewById(R.id.nextleveloneeasybutton);
        buttonNextLevel.setVisibility(View.INVISIBLE);
        img.setOnTouchListener(new View.OnTouchListener() {
            DragAndDrop dragAndDrop = new DragAndDrop(getApplicationContext(), img,vv,blankImg,layoutParams.leftMargin,layoutParams.topMargin,1);
            public boolean onTouch(View v, MotionEvent event) {

                boolean puzzleState = dragAndDrop.onTouch(v, event);
                if(puzzleState == true)
                    return true;
                if(puzzleState == false)
                {
                    blankImg.setImageResource(R.drawable.maintree);
                    img.setImageDrawable(null);
                    levelWinImg.setImageResource(R.drawable.levelwin);
                    Anim = AnimationUtils.loadAnimation(LevelOneEasy.this,R.anim.levelwinanimtext);
                    levelWinText.setText(R.string.level_win_text);
                    levelWinText.setAnimation(Anim);
                    buttonNextLevel.setVisibility(View.VISIBLE);
                    buttonNextLevel.setAnimation(Anim);
                    buttonNextLevel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            NextLevel();
                        }
                    });
                    return false;
                }

                return true;
            }

        });

        vv1 = (ViewGroup) findViewById(R.id.levelOneEasyPage);
        img1 = (ImageView)  vv.findViewById(R.id.notMainTreeLevelOneEasy);

        layoutParams1 = new RelativeLayout.LayoutParams(ConvertCoordinate.dpToPx(144),ConvertCoordinate.dpToPx(144));
        layoutParams1.leftMargin = dpToPx(250);
        layoutParams1.topMargin = dpToPx(300);
        img1.setLayoutParams(layoutParams1);

        img1.setOnTouchListener(new View.OnTouchListener() {
            DragAndDrop dragAndDrop = new DragAndDrop(getApplicationContext(), img1,vv1,blankImg,layoutParams1.leftMargin,layoutParams1.topMargin,2);
            public boolean onTouch(View v, MotionEvent event) {

                boolean puzzleState = dragAndDrop.onTouch(v, event);
                if(puzzleState == true)
                    return true;
                if(puzzleState == false)
                {
                    return false;
                }

                return true;
            }

        });

        /*buttonBacktoMenu = (Button) findViewById(R.id.backbuttontoMenu);
        buttonBacktoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        });*/
    }

    void Back() {
        System.out.println("buttonBacktoMenu");
        Intent intentBackk = new Intent(this, PuzzleMenuPage.class);
        startActivity(intentBackk);
    }

    void NextLevel() {

        Intent intentNext = new Intent(this, LevelTwoEasy.class);
        startActivity(intentNext);
    }

}