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

public class LevelFive extends AppCompatActivity {
    private Button buttonBacktoMenu;
    private Button buttonGoToMenu;
    private ImageView blankImg;
    private ImageView img;
    private ImageView levelWinImg;
    private TextView levelWinTextLayer1,levelWinTextLayer2;
    private ViewGroup vv;
    private RelativeLayout.LayoutParams layoutParams;
    private Animation Anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_five);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        blankImg = (ImageView)  findViewById(R.id.blueCapLevelFive);
        blankImg.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int x = dpToPx(event.getX());
                int y = dpToPx(event.getY());

                String message = String.format("Coordinates: (%d %d)",x,y);
                Log.d("ans: ",message);
                return false;
            }
        });


        vv = (ViewGroup) findViewById(R.id.levelFivePage);
        img = (ImageView)  vv.findViewById(R.id.mainCapLevelfive);

        layoutParams = new RelativeLayout.LayoutParams(ConvertCoordinate.dpToPx(100),ConvertCoordinate.dpToPx(100));
        layoutParams.leftMargin = dpToPx(150);
        layoutParams.topMargin = dpToPx(300);
        img.setLayoutParams(layoutParams);

        levelWinImg = (ImageView) findViewById(R.id.levelfivewinimage);
        levelWinTextLayer1 = (TextView) findViewById(R.id.levelfivewintextLayer1);
        levelWinTextLayer2 = (TextView) findViewById(R.id.levelfivewintextLayer2);

        buttonGoToMenu = (Button) findViewById(R.id.gotomenubutton);
        buttonGoToMenu.setVisibility(View.INVISIBLE);


        img.setOnTouchListener(new View.OnTouchListener() {
            DragAndDrop dragAndDrop = new DragAndDrop(getApplicationContext(), img,vv,blankImg,layoutParams.leftMargin,layoutParams.topMargin,1);
            public boolean onTouch(View v, MotionEvent event) {

                boolean puzzleState = dragAndDrop.onTouch(v, event);
                if(puzzleState == true)
                    return true;
                if(puzzleState == false)
                {
                    blankImg.setImageResource(R.drawable.cap);
                    img.setImageDrawable(null);
                    levelWinImg.setImageResource(R.drawable.levelwin);
                    Anim = AnimationUtils.loadAnimation(LevelFive.this,R.anim.levelwinanimtext);
                    levelWinTextLayer1.setText(R.string.last_level_win_text_layer1);
                    levelWinTextLayer1.setAnimation(Anim);

                    levelWinTextLayer2.setText(R.string.last_level_win_text_layer2);
                    levelWinTextLayer2.setAnimation(Anim);


                    buttonGoToMenu.setVisibility(View.VISIBLE);
                    buttonGoToMenu.setAnimation(Anim);
                    buttonGoToMenu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            GoToMenu();
                        }
                    });

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

    void GoToMenu() {
        System.out.println("buttonBacktoMenu");
        Intent intentBackk = new Intent(this, PuzzleMenuPage.class);
        startActivity(intentBackk);
    }

}