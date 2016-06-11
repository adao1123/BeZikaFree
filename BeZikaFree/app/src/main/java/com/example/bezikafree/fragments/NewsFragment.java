package com.example.bezikafree.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.bezikafree.NyTimesAPI.SearchAPI;
import com.example.bezikafree.R;
import com.example.bezikafree.adapters.RecyclerListener.RecyclerClickListener;
import com.example.bezikafree.adapters.RecyclerListener.RecyclerTouchListener;
import com.example.bezikafree.adapters.RecyclerViewAdapter;
import com.example.bezikafree.models.Doc;
import com.example.bezikafree.models.ZikaArticleSearch;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Billy on 6/11/16.
 */
public class NewsFragment extends Fragment{

    private RecyclerView recyclerView;
    private ProgressBar progress;
    private RecyclerViewAdapter zikaSearchAdapter;
    private ArrayList<Doc> articleSearchList;
    private SearchAPI articleSearchResponse;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_news_list,container,false);
        setViews(v);

        articleSearchList = new ArrayList<>();
        zikaSearchAdapter = new RecyclerViewAdapter(articleSearchList);

        searchBar();


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
                articleSearchList.get(position);
                Bundle article = new Bundle(); //will bundle the 5 fields of articleSearchObjects in a string array
                String[] articleDetails = {articleSearchList.get(position).getHeadline().getMain(),
                        articleSearchList.get(position).getWeb_url(),
                        articleSearchList.get(position).getMultimedia().toString(),
                        articleSearchList.get(position).getLead_paragraph()};
                article.putStringArray("searchedArticle", articleDetails);

                Fragment searchedArticleStory = new NewsArticleFragment();
                searchedArticleStory.setArguments(article);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_id, searchedArticleStory);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void searchBar(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.nytimes.com/svc/search/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        articleSearchResponse = retrofit.create(SearchAPI.class);
        Call<ZikaArticleSearch> call = articleSearchResponse.listArticleSearchDocs("Zika");
        call.enqueue(new Callback<ZikaArticleSearch>() {
            @Override
            public void onResponse(Call<ZikaArticleSearch> call, Response<ZikaArticleSearch> response) {
                ZikaArticleSearch articleSearchDocs = response.body();
                if(articleSearchDocs == null){
                    return;
                }

                Collections.addAll(articleSearchList, articleSearchDocs.getResponse().getDocs());

                if (recyclerView != null) {
                    recyclerView.setAdapter(zikaSearchAdapter);
                    progress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ZikaArticleSearch> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
