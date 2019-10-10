package com.sc4ever.bornali;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<CardStyle> cardStyleList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
    }
}
