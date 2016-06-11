package com.example.bezikafree.models;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Billy on 6/11/16.
 */
public class NewsWireObjects {

    private String section;
    private String title;
    private String url;
    private String thumbnail_standard;
    private String abstractResult;


    private static final String TAG = "NewsWireObjects ";


    public NewsWireObjects(String section, String title, String url, String thumbnail_standard, String abstractResult) {
        this.section = section;
        this.title = title;
        this.url = url;
        this.thumbnail_standard = thumbnail_standard;
        this.abstractResult = abstractResult;
    }


    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnail_standard() {
        return thumbnail_standard;
    }

    public String getAbstractResult() {
        return abstractResult;
    }

}
