package com.example.android.profileview;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ListItem> mListItem;


    public  class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.title_image)
            ;
        }
    }



    private ArrayList<String> mDataset;

    public MainAdapter( Context context,ArrayList<ListItem> ListItem) {
        mContext=context;
        mListItem= ListItem;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.row, parent ,false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        ListItem currentitem= mListItem.get(position);

        String imageurl = currentitem.getmImageurl();

        Picasso.with(mContext).load(imageurl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }


        }


