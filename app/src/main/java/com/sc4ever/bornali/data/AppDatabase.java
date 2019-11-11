package com.sc4ever.bornali.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = CardCategory.class, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "cards_db";
    public static AppDatabase instance;
    public abstract CardCategoryDao cardCategoryDao();
}
