package com.nikitayankov.rx;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleActivity extends AppCompatActivity {
    @BindView(R.id.web_view)
    WebView mWebView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    String mLink;
    String mTitle;
    String mCategory;

    // TODO: 29.07.2017 Is there any better way to do this ? That website is looks ugly :\
    // TODO: 29.07.2017 Maybe apply custom styles, or even parse html and get content
    @SuppressWarnings("ConstantConditions")
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        ButterKnife.bind(this);
        mTitle = getIntent().getStringExtra("title");
        mCategory = getIntent().getStringExtra("category");

        mToolbar.setTitle(mTitle);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mLink = getIntent().getStringExtra("link");

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(mLink);
    }

    @Override
    protected void onResume() {
        super.onResume();

        WebView.setWebContentsDebuggingEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
