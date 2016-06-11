package com.example.bezikafree.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.bezikafree.R;
import com.example.bezikafree.adapters.RecyclerListener.RecyclerClickListener;
import com.example.bezikafree.adapters.RecyclerListener.RecyclerTouchListener;

/**
 * Created by Billy on 6/11/16.
 */
public class NewsFragment extends Fragment{

    private RecyclerView recyclerView;
    private ProgressBar progress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_news_list,container,false);
        setViews(v);

        setRecyclerViewListener();
        return v;
    }

    private void setViews(View v){
        recyclerView = (RecyclerView)v.findViewById(R.id.news_alert_recycleView_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progress = (ProgressBar) v.findViewById(R.id.news_progress_bar_id);
    }



    private void setRecyclerViewListener(){
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

}
