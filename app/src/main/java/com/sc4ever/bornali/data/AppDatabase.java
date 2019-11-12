package com.sc4ever.bornali.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = CardCategory.class, exportSchema = false, version = 1)
 abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "cards_db";
    private static volatile AppDatabase instance;
    abstract CardCategoryDao cardCategoryDao();
    static AppDatabase getDatabaseInstance(Context context){
        if(instance == null){
            //synchronized access for thread safety
            synchronized (AppDatabase.class){
                if(instance == null){
                   instance = create(context); //if a instance doesn't exist, create a new one
                }
            }
        }
        return instance; //always returns a single instance
    }
    private static AppDatabase create(final Context context){
        //Use singleton approach to create a new database instance for performance and security
        //concerns
        return Room.databaseBuilder(context, AppDatabase.class,
                DB_NAME).fallbackToDestructiveMigration().build(); //returns the created database
        //fallbackMigration ensures any version update doesn't crash the app//
        // TODO add migration
    }
}
