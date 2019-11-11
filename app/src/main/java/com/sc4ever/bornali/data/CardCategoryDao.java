package com.sc4ever.bornali.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CardCategoryDao {
    @Query("Select * from card_categories")
    List<CardCategory> getCardCategoryList();
    @Query("select * from card_categories where cat_id in (:catIDs)")
    List<CardCategory> loadAllByIds(int[] catIDs);
    @Insert
    void insertCardCategory(CardCategory cardCategory);
    @Update
    void updateCardCategory(CardCategory cardCategory);
    @Delete
    void deleteCardCategory(CardCategory cardCategory);
}
