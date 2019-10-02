package com.sc4ever.bornali;

import
        android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    private List<CardStyle> cardStyleList;

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
                switch (position) {
                    case 0:
                        cardStyleList.clear();
                        prepareEnglishAlphabets();
                        cardAdapter.notifyDataSetChanged();
                        getSupportActionBar().setTitle("English Alphabets");
                        break;
                    case 1:
                        cardStyleList.clear();
                        prepareBengaliAlphabets();
                        cardAdapter.notifyDataSetChanged();
                        getSupportActionBar().setTitle("Bengali Alphabets");
                        break;
                    case 2:
                        cardStyleList.clear();
                        prepareFoodsCategory();
                        cardAdapter.notifyDataSetChanged();
                        getSupportActionBar().setTitle("Foods");
                        break;
                    case 3:
                        cardStyleList.clear();
                        prepareClothesCategory();
                        cardAdapter.notifyDataSetChanged();
                        getSupportActionBar().setTitle("Clothes");
                        break;

                    case 4:
                        cardStyleList.clear();
                        prepareToiletries();
                        cardAdapter.notifyDataSetChanged();
                        getSupportActionBar().setTitle("Toiletries");
                        break;
                    case 5:
                        cardStyleList.clear();
                        prepareAcademicEssentials();
                        cardAdapter.notifyDataSetChanged();
                        getSupportActionBar().setTitle("Academic Essentials");
                        break;

                }
            }
        });
        prepareCards();
    }

    private void prepareCards() {
        cardStyleList.add(new CardStyle(R.drawable.alphabets, "English Alphabets"));
        cardStyleList.add(new CardStyle(R.drawable.alphabets_bengali, "Bengali Alphabets"));
        cardStyleList.add(new CardStyle(R.drawable.rsz_1food, "Foods"));
        cardStyleList.add(new CardStyle(R.drawable.tee_shirt, "Clothes"));
        cardStyleList.add(new CardStyle(R.drawable.tooth_brush, "Toiletries"));
        cardStyleList.add(new CardStyle(R.drawable.backpack, "Academic Essentials"));
    }

    private void prepareClothesCategory() {
        cardStyleList.add(new CardStyle(R.drawable.tee_shirt, "Shirt"));
        cardStyleList.add(new CardStyle(R.drawable.sweater, "Sweater"));
        cardStyleList.add(new CardStyle(R.drawable.shorts, "Shorts"));
        cardStyleList.add(new CardStyle(R.drawable.lifejacket, "Life Jacket"));
        cardStyleList.add(new CardStyle(R.drawable.underwear, "Under Wear"));
        cardStyleList.add(new CardStyle(R.drawable.hat, "Cap"));
        cardStyleList.add(new CardStyle(R.drawable.winter_hat, "Winter Hat"));
        cardStyleList.add(new CardStyle(R.drawable.shoes, "Shoes"));
        cardStyleList.add(new CardStyle(R.drawable.sandals, "Sandals"));
        cardStyleList.add(new CardStyle(R.drawable.socks, "Socks"));
    }

    private void prepareFoodsCategory() {
        cardStyleList.add(new CardStyle(R.drawable.rice, "Rice"));
        cardStyleList.add(new CardStyle(R.drawable.egg1, "Egg"));
        cardStyleList.add(new CardStyle(R.drawable.singara, "Singara"));
        cardStyleList.add(new CardStyle(R.drawable.somusa, "Somusa"));
    }

    private void prepareToiletries() {
        cardStyleList.add(new CardStyle(R.drawable.tooth_brush, "Tooth Brush"));
        cardStyleList.add(new CardStyle(R.drawable.paste, "Tooth Paste"));
    }

    private void prepareAcademicEssentials() {
        cardStyleList.add(new CardStyle(R.drawable.backpack, "Bag"));
        cardStyleList.add(new CardStyle(R.drawable.pencil, "Pencil"));
        cardStyleList.add(new CardStyle(R.drawable.scissors, "Scissorss"));
    }

    private void prepareEnglishAlphabets() {
        cardStyleList.add(new CardStyle(R.drawable.a_ant, "A"));
        cardStyleList.add(new CardStyle(R.drawable.b_bus, "B"));
        cardStyleList.add(new CardStyle(R.drawable.c_cat, "C"));
        cardStyleList.add(new CardStyle(R.drawable.s_sun, "S"));
    }

    private void prepareBengaliAlphabets() {
        cardStyleList.add(new CardStyle(R.drawable.shor_o, "Shor O"));
        cardStyleList.add(new CardStyle(R.drawable.shor_aa, "Shor aa"));
        cardStyleList.add(new CardStyle(R.drawable.ka, "ka"));
        cardStyleList.add(new CardStyle(R.drawable.kha, "kha"));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        if(getSupportActionBar().getTitle().equals(getString(R.string.title_card_category))){
            finish();
        } else {
            cardStyleList.clear();
            getSupportActionBar().setTitle(R.string.title_card_category);
            prepareCards();
            cardAdapter.notifyDataSetChanged();
        }
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
