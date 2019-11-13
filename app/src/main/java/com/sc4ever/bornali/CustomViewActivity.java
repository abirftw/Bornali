package com.sc4ever.bornali ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_acitvity);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void listener(){
        RelativeLayout rootLayout = findViewById(R.id.practiceCanvas);
        final Alphabets currentAlphabet = new Alphabets() ;
        final PracticeCanvas practiceCanvas = new PracticeCanvas(this);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practice, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            Intent intent = new Intent(CustomViewActivity.this, HelpPageActivity.class) ;
            startActivity(intent);
        }
       /* if(id == R.id.action_next){
            update
            practiceCanvas.invalidate();
        }
        if(id == R.id.action_previous){
            practiceCanvas.pathList.clear();
            practiceCanvas.invalidate();
            practiceCanvas.updatePath(practiceCanvas.alphabetALine1.get(0).getCoordinateX(),practiceCanvas.alphabetALine1.get(0).getCoordinateY());
        }*/

        if(id==R.id.action_home){
            Intent intent = new Intent(CustomViewActivity.this, HomePageActivity.class) ;
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean compare (float x1 , float y1 , float x2 , float y2) {
        return  (x2 >= x1 - 15 && x2 <= x1 + 15 && y2 >= y1 - 15 && y2 <= y1 + 15 );
    }


}