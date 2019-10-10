package com.sc4ever.bornali;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardCategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private ArrayList<CardStyle> cardStyleList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.rv_card_category);
        cardStyleList = new ArrayList<>();
        cardAdapter = new CardAdapter(cardStyleList, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardAdapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                switch (position){
                    case 0:
                        Intent intent = new Intent("com.sc4ever.bornali.CardListActivity");
                        intent.putParcelableArrayListExtra("cardList", cardStyleList);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        });
        prepareCards();
    }
    private void cat01()
    {
        cardStyleList.add(new CardStyle(R.drawable.rsz_sleep, "Sleep"));
    }
    private void prepareCards(){
        cardStyleList.add(new CardStyle(R.drawable.flash23, "Category A"));
        cardStyleList.add(new CardStyle(R.drawable.logo, "Category B"));
        cardStyleList.add(new CardStyle(R.drawable.rsz_caretaker, "Category C"));
        cardStyleList.add(new CardStyle(R.drawable.rsz_sleep, "Category D"));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
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
            Intent intent = new Intent(CardCategoryActivity.this, HelpPageActivity.class) ;
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
