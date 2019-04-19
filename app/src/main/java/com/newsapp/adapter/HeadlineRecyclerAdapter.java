package com.newsapp.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newsapp.R;
import com.newsapp.activity.HeadlinesActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class HeadlineRecyclerAdapter extends RecyclerView.Adapter<HeadlineRecyclerAdapter.ViewHolder> {


    JSONObject jsonObject = new JSONObject();
    HeadlinesActivity activity;

    public HeadlineRecyclerAdapter(HeadlinesActivity headlinesActivity, JSONObject response) {
        this.activity= headlinesActivity;
        this.jsonObject=response;
    }

    @NonNull
    @Override
    public HeadlineRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
       return new HeadlineRecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newsdetail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HeadlineRecyclerAdapter.ViewHolder viewHolder, final int i) {

        try {
            Picasso.get().load(jsonObject.getJSONArray("articles").getJSONObject(i).getString("urlToImage"))
                    .into(viewHolder.newsImage);

            viewHolder.newsTitle.setText(jsonObject.getJSONArray("articles").getJSONObject(i).getString("title"));
            viewHolder.newsDesc.setText(jsonObject.getJSONArray("articles").getJSONObject(i).getString("description"));

            viewHolder.mainlvheadline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = null;
                    try {
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(jsonObject.getJSONArray("articles").getJSONObject(i).getString("url")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                   activity.startActivity(browserIntent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        try {
            return jsonObject.getJSONArray("articles").length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView newsTitle, newsDesc;
       ConstraintLayout mainlvheadline;
       ImageView newsImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.newsImage);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsDesc = itemView.findViewById(R.id.newsDesc);
            mainlvheadline = itemView.findViewById(R.id.mainlvheadline);

        }
    }
}
