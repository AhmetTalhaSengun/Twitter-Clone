package com.sengun.twitterlast.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sengun.twitterlast.databinding.RecyclerRowBinding;
import com.sengun.twitterlast.model.Tweet;

import java.util.ArrayList;

public class TweetAdepter extends RecyclerView.Adapter<TweetAdepter.TweetHolder> {

    private ArrayList<Tweet> tweetArrayList;

    public TweetAdepter(ArrayList<Tweet> tweetArrayList){
        this.tweetArrayList = tweetArrayList;
    }
    @NonNull
    @Override
    public TweetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TweetHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetHolder holder, int position) {
        holder.recyclerRowBinding.recyclerUserEmailText.setText(tweetArrayList.get(position).email);
        holder.recyclerRowBinding.recyclerTweetText.setText(tweetArrayList.get(position).tweet);

    }

    @Override
    public int getItemCount() {
     return tweetArrayList.size();
    }

    class TweetHolder extends RecyclerView.ViewHolder{

        RecyclerRowBinding recyclerRowBinding;

        public TweetHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding= recyclerRowBinding;
        }
    }


}
