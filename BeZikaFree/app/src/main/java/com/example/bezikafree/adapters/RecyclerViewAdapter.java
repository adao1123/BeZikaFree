package com.example.bezikafree.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bezikafree.R;

import java.util.ArrayList;

/**
 * Created by Billy on 6/11/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<String> data; // replace String with dataModel
    private Context context;
    private static OnItemClickListener listener;

    public RecyclerViewAdapter(ArrayList<String> data) {
        this.data = data;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    // this is where we setup TextView and item clicker
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewHolder (final View itemView) {
            super(itemView);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {


    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_custom_layout, parent, false);
        RecyclerViewHolder vh = new RecyclerViewHolder(view);
        return vh;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
