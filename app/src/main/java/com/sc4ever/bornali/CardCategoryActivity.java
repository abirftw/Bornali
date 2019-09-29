package com.sc4ever.bornali;

import android.app.Activity;
import android.os.Bundle;

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
        recyclerView = findViewById(R.id.rv_card_category);
        cardStyleList = new ArrayList<>();
        cardAdapter = new CardAdapter(cardStyleList, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardAdapter);
        prepareCards();
    }
    private void prepareCards(){
        cardStyleList.add(new CardStyle(R.drawable.flash23, "Category A"));
        cardStyleList.add(new CardStyle(R.drawable.logo, "Category B"));
        cardStyleList.add(new CardStyle(R.drawable.rsz_caretaker, "Category C"));
        cardStyleList.add(new CardStyle(R.drawable.rsz_sleep, "Category D"));

    }
}
