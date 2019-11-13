package com.sc4ever.bornali.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/*
 Class to handle all the DDL and DML queries to the database
 */

public class CardCategoryRepository {
    private AppDatabase appDatabase;
    public CardCategoryRepository(Context context){
        appDatabase = AppDatabase.getDatabaseInstance(context);
    }
    public void insertCard(String cardText, String URI){
        insertCard(cardText, URI, 0); //If a card is a category and thus isn't part of
                                                //any category
    }
    public void insertCard(String cardText, String URI, int partOFID){
        CardCategory cardCategory = new CardCategory();
        cardCategory.setID(CardCategory.getCardCount());
        CardCategory.increment();
        cardCategory.setPartOFID(partOFID);
        cardCategory.setImgURI(URI);
        cardCategory.setText(cardText);
        insertCard(cardCategory);
    }
    private void insertCard(final CardCategory cardCategory){
        //execute the insert operation in the background
        // TODO implement static Async
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.cardCategoryDao().insertCardCategory(cardCategory);
                return null;
            }
        }.execute();
    }
    public LiveData<List<CardCategory>> getAllCards(int id){
        //for a single category, -1 is used to load all the category
        return appDatabase.cardCategoryDao().getAllCards(id);
    }

}
