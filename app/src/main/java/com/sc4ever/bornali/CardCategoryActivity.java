package com.sc4ever.bornali;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


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
    private static final int RESULT_LOAD_CARD = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prepareCards();
        recyclerView = findViewById(R.id.rv_card_category);
        cardStyleList = new ArrayList<>();
        cardAdapter = new CardAdapter(cardStyleList, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardAdapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent("com.sc4ever.bornali.CardListActivity");
                intent.putExtra("Title", cardStyleList.get(position).getCardText());
                intent.putExtra("ID", cardStyleList.get(position).getCardID());
                startActivity(intent);
            }
        });
    }
    private void prepareCards(){
       AsyncUpdateList updateList = new AsyncUpdateList();
       updateList.execute("0");
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(CardCategoryActivity.this,
                            AddCardActivity.class) ;
                    final CardCategoryRepository repository = new
                            CardCategoryRepository(getApplicationContext());
                    int catCount = repository.getAllCardCount();
                    intent.putExtra("CatCount", catCount);
                    intent.putExtra("partOF", 0);
                    startActivityForResult(intent, RESULT_LOAD_CARD);
                }
            }).start();
        }

        return super.onOptionsItemSelected(item);
    }
    private class AsyncUpdateList extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... strings) {
            final CardCategoryRepository repository = new
                    CardCategoryRepository(getApplicationContext());
            final List<CardCategory> cardCategories;
            cardCategories = repository.getAllCardsByID(Integer.parseInt(strings[0]));
            for (CardCategory cardCategory : cardCategories){
                cardStyleList.add(new CardStyle(cardCategory.getID(),
                        cardCategory.getImgURI(), cardCategory.getText()));
            }
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_CARD){
            prepareCards();
            cardAdapter.notifyDataSetChanged();
        }
    }
}
