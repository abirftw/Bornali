package com.sc4ever.bornali;

import java.util.List;

public class CardStyle {
    private int cardImage;
    private String cardText;

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
