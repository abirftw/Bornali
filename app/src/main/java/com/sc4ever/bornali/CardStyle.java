package com.sc4ever.bornali;

import android.os.Parcel;
import android.os.Parcelable;

public class CardStyle implements Parcelable {
    private int cardImage;
    private String cardURI;
    private String cardText;

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(cardImage);
        parcel.writeString(cardText);
        parcel.writeString(cardURI);
    }
    public static final Creator<CardStyle> CREATOR = new Creator<CardStyle>() {
        @Override
        public CardStyle createFromParcel(Parcel parcel) {
            return new CardStyle(parcel);
        }

        @Override
        public CardStyle[] newArray(int i) {
            return new CardStyle[i];
        }
    };
    private CardStyle(Parcel in) {
        cardImage = in.readInt();
        cardText = in.readString();
        cardURI = in.readString();
    }
    CardStyle(int cardImage, String cardText) {
        this.cardImage = cardImage;
        this.cardText = cardText;
        this.cardURI = "";
    }

    CardStyle(String cardURI, String cardText) {
        this.cardURI = cardURI;
        this.cardText = cardText;
        this.cardImage = -1;
    }

    int getCardImage() {
        return cardImage;
    }

    String getCardText() {
        return cardText;
    }

    String getCardURI() {
        return cardURI;
    }
}
