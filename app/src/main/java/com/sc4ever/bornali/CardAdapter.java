package com.sc4ever.bornali;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<CardStyle> cardList;
    private Context mContext;
    CardAdapter(List<CardStyle> cardList, Context mContext) {
        this.cardList = cardList;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cardText;
        ImageView cardImage;
        public RelativeLayout cardRow ;
        ViewHolder(View view){
            super(view);
            cardText = view.findViewById(R.id.card_title);
            cardImage = view.findViewById(R.id.card_image);

        }
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cardView = inflater.inflate(R.layout.card_row_item,parent,false);
        return new  ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        CardStyle card = cardList.get(position);
        holder.cardText.setText(card.getCardText());
        if(card.getCardURI().equals("")){
            Glide.with(mContext).load(card.getCardImage()).into(holder.cardImage);
        } else {
            Uri uri = Uri.parse(card.getCardURI());
            Glide.with(mContext).load(uri).into(holder.cardImage);
        }
    }
}
