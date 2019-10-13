package com.sc4ever.bornali;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardListActivity extends AppCompatActivity {
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
        cardStyleList = getIntent().getParcelableArrayListExtra("cardList");
        cardAdapter = new CardAdapter(cardStyleList, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardAdapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                popUP(cardStyleList.get(position));
            }
        });
    }
    private void popUP(CardStyle cardStyle) {
        AlertDialog.Builder alert = new AlertDialog.Builder(CardListActivity.this);
        alert.setTitle(cardStyle.getCardText());
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.card_row_item, null);
        ImageView imageView = view.findViewById(R.id.card_image);
        TextView textView = view.findViewById(R.id.card_title);
        textView.setText(cardStyle.getCardText());
        imageView.setImageResource(cardStyle.getCardImage());
        alert.setView(view);
        alert.show();
    }
}
