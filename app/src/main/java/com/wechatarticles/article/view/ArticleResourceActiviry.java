package com.wechatarticles.article.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.wechatarticles.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuhui on 2017/5/23.
 */

public class ArticleResourceActiviry extends Activity {
    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_resource);
        ButterKnife.bind(this);
        String url=getIntent().getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
