package com.newsapp.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TextView;

import com.newsapp.R;
import com.newsapp.activity.MainActivity;
import com.newsapp.activity.HeadlinesActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SourceRecyclerAdapter extends RecyclerView.Adapter<SourceRecyclerAdapter.ViewHolder> {

    JSONObject jsonObject = new JSONObject();
    MainActivity activity;
    public SourceRecyclerAdapter(MainActivity mainActivity, JSONObject response) {
        this.activity = mainActivity;
        this.jsonObject= response;
    }

    @NonNull
    @Override
    public SourceRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new SourceRecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_source, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SourceRecyclerAdapter.ViewHolder viewHolder, final int i) {
        try {
            viewHolder.descSource.setText(jsonObject.getJSONArray("sources").getJSONObject(i).getString("description"));
            viewHolder.nameSource.setText(jsonObject.getJSONArray("sources").getJSONObject(i).getString("name"));

            viewHolder.cardSource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, HeadlinesActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        intent.putExtra("id",jsonObject.getJSONArray("sources").getJSONObject(i).getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    activity.startActivity(intent);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        try {
            return jsonObject.getJSONArray("sources").length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView descSource, nameSource;
        CardView cardSource;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            descSource= itemView.findViewById(R.id.descSource);
            nameSource= itemView.findViewById(R.id.nameSource);
            cardSource= itemView.findViewById(R.id.cardSource);

        }
    }
}
