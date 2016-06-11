package com.example.bezikafree.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bezikafree.R;
import com.example.bezikafree.models.Doc;
import com.example.bezikafree.models.Multimedia;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Billy on 6/11/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<Doc> data; // replace String with dataModel
    private Context context;


    private static OnItemClickListener listener;

    public RecyclerViewAdapter(ArrayList<Doc> data) {
        this.data = data;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    // this is where we setup TextView and item clicker
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {


        TextView headline;
        ImageView imageIcon;
        TextView articleAbstract;
        Doc doc;


        public RecyclerViewHolder (final View itemView) {
            super(itemView);
            headline = (TextView) itemView.findViewById(R.id.news_headline_id);
            imageIcon = (ImageView)itemView.findViewById(R.id.news_cardView_image_id);
            articleAbstract = (TextView)itemView.findViewById(R.id.news_article_info_id);
            doc = new Doc();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.headline.setText(data.get(position).getHeadline().getMain());
        holder.articleAbstract.setText(data.get(position).getLead_paragraph());

        String imageURI = null;

        Multimedia[] multiMedia = data.get(position).getMultimedia();
        if(multiMedia != null && multiMedia.length > 0) {
            imageURI = multiMedia[0].getUrl();
        }
        if (imageURI == null) {
            imageURI = "R.drawable.nyt_icon";
        }

        Picasso.with(context)
                .load("http://nytimes.com/" + imageURI)
                .placeholder(R.drawable.nyt_icon)
                .resize(100, 100)
                .centerCrop()
                .into(holder.imageIcon);

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



    public void setData(ArrayList<Doc> data) {
        this.data = data;
    }
}
