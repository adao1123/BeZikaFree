package com.example.bezikafree.adapters.RecyclerListener;

import android.view.View;

/**
 * Created by Billy on 6/11/16.
 */
public interface RecyclerClickListener {

    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
