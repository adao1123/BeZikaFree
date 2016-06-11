package com.example.bezikafree.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.ActionMenuItemView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bezikafree.R;

/**
 * Created by Billy on 6/11/16.
 */
public class NewsArticleFragment extends Fragment {

    ActionMenuItemView share;
    String[] articleDetails;
    View v;

    private static final String TAG = "ArticleStory Fragment";
    private ProgressBar progress;
    private WebView articleWebView;
    private String htmlSaveForLater;
    private SQLiteDatabase db;
    private MenuItem saveLater;

    /**
     * user interface to callback for fragment
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.news_article_fragment, container, false);
        articleWebView = (WebView) v.findViewById(R.id.article_web_view);

        Bundle article = getArguments();

        articleDetails = article.getStringArray("searchedArticle");

        progress = (ProgressBar) v.findViewById(R.id.progress_bar);

        WebSettings webSettings = articleWebView.getSettings();
        articleWebView.setWebViewClient(new WebViewClientDemo()); //opens url in app, not in default browser
        webSettings.setJavaScriptEnabled(true); //turn js on for hacking and giving better ux
        articleWebView.addJavascriptInterface(new htmlJavaScriptInterface(), "HTMLOUT");

        articleWebView.loadUrl(articleDetails[1]);


        return v;

    }


    private class WebViewClientDemo extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progress.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            articleWebView.loadUrl("javascript:window.HTMLOUT.showHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            progress.setVisibility(View.GONE);
            saveLater.setVisible(true);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progress.setVisibility(View.VISIBLE);
            saveLater.setVisible(false);
        }
    }

    public class htmlJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void showHTML(String html) {
            htmlSaveForLater = html;
        }
    }

}
