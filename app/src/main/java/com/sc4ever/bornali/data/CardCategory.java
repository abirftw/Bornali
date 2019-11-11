package com.sc4ever.bornali.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "card_categories"
)
public class CardCategory {
     @PrimaryKey(autoGenerate = true)
     @ColumnInfo(name = "cat_id", index = true)
     private int ID;
     @ColumnInfo (name = "cat_desc")
     private String text;
     @ColumnInfo (name = "cat_uri")
     private String imgURI;

     public CardCategory(int ID, String text, String imgURI) {
          this.ID = ID;
          this.text = text;
          this.imgURI = imgURI;
     }
}
