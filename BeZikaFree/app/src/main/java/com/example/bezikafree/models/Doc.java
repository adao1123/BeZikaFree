package com.example.bezikafree.models;

/**
 * Created by Billy on 6/11/16.
 */
public class Doc {
    private String lead_paragraph;
    private String web_url;
    private String section_name;
    private Headlines headline;
    private Multimedia[] multimedia;
    private String pub_date;

    public String getLead_paragraph() {
        return lead_paragraph;
    }

    public String getWeb_url() {
        return web_url;
    }

    public Headlines getHeadline() {
        return headline;
    }

    public Multimedia[] getMultimedia() {
        return multimedia;
    }
}
