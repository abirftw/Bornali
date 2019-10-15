package com.sc4ever.bornali;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CardListActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private ArrayList<CardStyle> cardStyleList;
    private TextToSpeech tts;
    private int DATA_CHECK = 0;
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
        Intent checkTTS = new Intent();
        checkTTS.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTS, DATA_CHECK);
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
        speak(textView.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == DATA_CHECK){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                tts = new TextToSpeech(this, this);
            } else  {
                Intent installTTS = new Intent();
                installTTS.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTS);
            }
        }
    }
    private void speak(String word) {
        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }
    @Override
    public void onInit(int i) {
        if(i == TextToSpeech.SUCCESS){
            if(tts.isLanguageAvailable(Locale.ENGLISH) == TextToSpeech.LANG_AVAILABLE){
                tts.setLanguage(Locale.ENGLISH);
            }
        } else if(i == TextToSpeech.ERROR){
            Toast.makeText(this, "Can't find TTS", Toast.LENGTH_LONG).show();
        }
    }
}
