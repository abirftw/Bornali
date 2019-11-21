package com.sc4ever.bornali.data;

import android.content.Context;
import android.os.AsyncTask;
import java.util.List;

/*
 Class to handle all the DDL and DML queries to the database
 */

public class CardCategoryRepository {
    private AppDatabase appDatabase;
    public CardCategoryRepository(Context context){
        appDatabase = AppDatabase.getDatabaseInstance(context);
    }
    public void insertCard(int id, String cardText, String URI){
        insertCard(id, cardText, URI, 0); //If a card is a category and thus isn't part of
                                                //any category
    }
    public void insertCard(int id, String cardText, String URI, int partOFID){
        CardCategory cardCategory = new CardCategory();
        cardCategory.setID(id);
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
    public List<CardCategory> getAllCardsByID(int id){
        return appDatabase.cardCategoryDao().getAllCards(id);
    }
    public void updateCard(String cardText, String cardURI, int id, int partOFID){
        CardCategory cardCategory = new CardCategory();
        cardCategory.setPartOFID(partOFID);
        cardCategory.setID(id);
        cardCategory.setText(cardText);
        cardCategory.setImgURI(cardURI);
        updateCard(cardCategory);

    }
    private void updateCard(final CardCategory cardCategory){
        //execute the update operation in the background
        // TODO implement static Async
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.cardCategoryDao().updateCardCategory(cardCategory);
                return null;
            }
        }.execute();
    }
    public int getAllCardCount(){
        return appDatabase.cardCategoryDao().getAllCardCount();
    }

}
