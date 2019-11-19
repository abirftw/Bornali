package com.sc4ever.bornali;

import
        android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sc4ever.bornali.data.CardCategory;
import com.sc4ever.bornali.data.CardCategoryRepository;

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
                Intent intent = new Intent("com.sc4ever.bornali.CardListActivity");
                switch (position){
                    case 0:
                        cardStyleList.clear();
                        intent.putExtra("cardTitle", "A");
                        cat01();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
                intent.putParcelableArrayListExtra("cardList", cardStyleList);
                startActivity(intent);
            }
        });
        prepareCards();
    }
    private void cat01()
    {
        cardStyleList.add(new CardStyle(R.drawable.rsz_sleep, "Sleep"));
    }
    private void prepareCards(){
        AsyncUpdateList x = new AsyncUpdateList();
        x.execute();
        cardStyleList.add(new CardStyle(R.drawable.flash23, "Category A"));
        cardStyleList.add(new CardStyle(R.drawable.logo, "Category B"));
        cardStyleList.add(new CardStyle(R.drawable.rsz_caretaker, "Category C"));
        cardStyleList.add(new CardStyle(R.drawable.rsz_sleep, "Category D"));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cardcategory, menu);
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
        } else if(id == R.id.action_add_category){
            Intent intent = new Intent(CardCategoryActivity.this, AddCardActivity.class) ;
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    private class AsyncUpdateList extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            final CardCategoryRepository repository = new
                    CardCategoryRepository(getApplicationContext());
            final List<CardCategory> cardCategories;
            cardCategories = repository.getAllCardsByID(0);
            for (CardCategory cardCategory : cardCategories){
                Log.d("Path", cardCategory.getImgURI());
                cardStyleList.add(new CardStyle(cardCategory.getImgURI(), cardCategory.getText()));
            }
            return null;
        }
    }
}
