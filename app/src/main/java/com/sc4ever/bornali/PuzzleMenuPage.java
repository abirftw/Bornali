package com.sc4ever.bornali;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PuzzleMenuPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
ImageButton buttonPicture;
TextView presentleveltext;
int id = -111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_menu_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presentleveltext = (TextView) findViewById(R.id.PresentLevelText);
        buttonPicture = (ImageButton) findViewById(R.id.LevelOneButton);
        buttonPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterLevel();
            }
        });
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.puzzle_menu_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
         id = item.getItemId();

                if (id == R.id.nav_levelOne)
        {
            presentleveltext.setText("Level One");
            buttonPicture.setImageResource(R.drawable.samplelevelone);

        }
                else if (id == R.id.nav_levelTwo)
        {
            presentleveltext.setText("Level Two");
            buttonPicture.setImageResource(R.drawable.bird);
        }
                else if (id == R.id.nav_levelThree)
        {
            presentleveltext.setText("Level Three");
            buttonPicture.setImageResource(R.drawable.levelonebird);
        }
                else if (id == R.id.nav_levelFour)
        {
            presentleveltext.setText("Level Four");
            buttonPicture.setImageResource(R.drawable.levelonebird_blank);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void EnterLevel() {
        System.out.println("buttonBacktoMenu");
        Intent intentBack = null;
        if( id == -111)
        {
             intentBack = new Intent(this, LevelOne.class);
        }
        if (id == R.id.nav_levelOne)
        {
             intentBack = new Intent(this, LevelOne.class);
        }
        else if (id == R.id.nav_levelTwo)
        {
             intentBack = new Intent(this, LevelTwo.class);
        }
        startActivity(intentBack);
    }
}
