package com.newsapp.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newsapp.R;
import com.newsapp.ui.home.MainActivity;
import com.newsapp.config.Setting;
import com.squareup.picasso.Picasso;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {



    @NonNull
    @Override
    public CategoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new CategoryRecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerAdapter.ViewHolder viewHolder, int i) {

        viewHolder.nameTv.setText(Setting.COUNTRY.keySet().toArray()[i]+"");
        Log.e("onBindViewHolder: ",Setting.COUNTRY.keySet().toArray()[i]+"" );
        final String key =Setting.COUNTRYFLAG.keySet().toArray()[i]+"";

        viewHolder.cardCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.selectLang(Setting.COUNTRY.get(key));
            }
        });

        Picasso.get().load(Setting.COUNTRYFLAG.get(key)).into(viewHolder.flag);

    }

    @Override
    public int getItemCount() {
        return Setting.COUNTRY.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv;
        CardView cardCountry;
        ImageView flag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.name);
            cardCountry= itemView.findViewById(R.id.cardCountry);
            flag= itemView.findViewById(R.id.flag);
        }
    }
}
