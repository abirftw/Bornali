package com.sc4ever.bornali;

import android.os.Parcel;
import android.os.Parcelable;

public class CardStyle implements Parcelable {
    private int cardImage;
    private String cardText;

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(cardImage);
        parcel.writeString(cardText);
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
    }
    public CardStyle(int cardImage, String cardText) {
        this.cardImage = cardImage;
        this.cardText = cardText;
    }

    public int getCardImage() {
        return cardImage;
    }

    public void setCardImage(int cardImage) {
        this.cardImage = cardImage;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }
}
