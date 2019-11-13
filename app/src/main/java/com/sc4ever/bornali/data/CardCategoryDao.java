package com.sc4ever.bornali.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CardCategoryDao {
    //getting all the cards of a single categories
    //-1 is used to load all the categories
    @Query("Select * from card_categories where part_of_id = :id")
    LiveData<List<CardCategory>> getAllCards(int id);
    @Insert
    void insertCardCategory(CardCategory cardCategory);
    @Update
    void updateCardCategory(CardCategory cardCategory);
    @Delete
    void deleteCardCategory(CardCategory cardCategory);
}
