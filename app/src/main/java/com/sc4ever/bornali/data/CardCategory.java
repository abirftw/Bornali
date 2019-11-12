package com.sc4ever.bornali.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "card_categories",
        foreignKeys = @ForeignKey(entity = CardCategory.class, parentColumns = "cat_id",
                childColumns = "part_of_id", onDelete = ForeignKey.CASCADE
        )
)
public class CardCategory {
     @PrimaryKey
     @ColumnInfo(name = "cat_id")
     private int ID;
     @ColumnInfo (name = "cat_desc")
     private String text;
     @ColumnInfo (name = "cat_uri")
     private String imgURI;
     @ColumnInfo(name = "part_of_id", index = true)
     private int partOFID;
     private static int cardCount = 0;

     static int getCardCount() {
        return cardCount;
    }
     static void increment(){
        ++cardCount;
    }

    public int getID() {
        return ID;
    }

    void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    void setText(String text) {
        this.text = text;
    }

    public String getImgURI() {
        return imgURI;
    }

    void setImgURI(String imgURI) {
        this.imgURI = imgURI;
    }

    public int getPartOFID() {
        return partOFID;
    }

    void setPartOFID(int partOFID) {
        this.partOFID = partOFID;
    }
}
