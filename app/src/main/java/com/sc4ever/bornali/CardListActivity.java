package com.sc4ever.bornali;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sc4ever.bornali.data.CardCategory;
import com.sc4ever.bornali.data.CardCategoryRepository;

import java.util.ArrayList;

import java.util.List;
import java.util.Locale;

public class CardListActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private ArrayList<CardStyle> cardStyleList;
    private TextToSpeech tts;
    private int curCatID;
    int catCount = 0;
    private static final int RESULT_LOAD_CARD = 1;
    private int DATA_CHECK = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        assert getSupportActionBar() != null;
        //enabling universal back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.rv_card_category);
        cardStyleList = new ArrayList<>();
        //loading activity title from the previous intent
        getSupportActionBar().setTitle(getIntent().getStringExtra("Title"));
        //adding all the cards from the previous intent
        //loading the array in the recycle view adapter
        cardAdapter = new CardAdapter(cardStyleList, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        //grid layout for displaying more information
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardAdapter);
        curCatID = getIntent().getIntExtra("ID", 0);
        prepareCards(curCatID);
        new AsyncGetCardCount().execute();
        Intent checkTTS = new Intent();
        //checking whether tts is installed
        checkTTS.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTS, DATA_CHECK);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                //pop up the card and speak its content on click
                popUP(cardStyleList.get(position));
            }
        });
    }
    private void popUP(CardStyle cardStyle) {
        AlertDialog.Builder alert = new AlertDialog.Builder(CardListActivity.this);
        alert.setTitle(cardStyle.getCardText());
        //custom layout for the pop up window
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.card_row_item, null);
        //image view and the text view for the card
        ImageView imageView = view.findViewById(R.id.card_image);
        TextView textView = view.findViewById(R.id.card_title);
        textView.setText(cardStyle.getCardText());
        Uri uri = Uri.parse(cardStyle.getCardURI());
        Glide.with(this).load(uri).into(imageView);
        alert.setView(view);
        alert.show();
        //speaking the card's contents
        speak(cardStyle.getCardText());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //result for the tts installed action
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == DATA_CHECK){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                tts = new TextToSpeech(this, this);
            } else  {
                //prompt for new installation
                Intent installTTS = new Intent();
                installTTS.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTS);
            }
        } else if(requestCode == RESULT_LOAD_CARD){
            if(resultCode == RESULT_OK){
                prepareCards(curCatID);
                cardAdapter.notifyDataSetChanged();
            }
        }
    }
    private void speak(String word) {
        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }
    @Override
    public void onInit(int i) {
        if(i == TextToSpeech.SUCCESS){
            //set tts language
            if(tts.isLanguageAvailable(Locale.ENGLISH) == TextToSpeech.LANG_AVAILABLE){
                tts.setLanguage(Locale.ENGLISH);
            }
        } else if(i == TextToSpeech.ERROR){
            Toast.makeText(this, "Can't find TTS", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        //app bar navigation back key
        finish();
        return true;
    }
    private class AsyncUpdateList extends AsyncTask<String, Void, Void> {
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
    private class AsyncGetCardCount extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            final CardCategoryRepository repository = new
                    CardCategoryRepository(getApplicationContext());
            catCount = repository.getAllCardCount();
            return null;
        }
    }
    private void prepareCards(int ID){
        AsyncUpdateList updateList = new AsyncUpdateList();
        updateList.execute(String.valueOf(ID));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cardlist, menu);
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
            Intent intent = new Intent(CardListActivity.this, HelpPageActivity.class) ;
            startActivity(intent);
        } else if(id == R.id.action_add_category){
            Intent intent = new Intent(CardListActivity.this,
                    AddCardActivity.class) ;
            intent.putExtra("CatCount", catCount);
            intent.putExtra("partOF", curCatID);
            startActivityForResult(intent, RESULT_LOAD_CARD);
        }

        return super.onOptionsItemSelected(item);
    }
}
