package com.wechatarticles.article.presenter;

import com.wechatarticles.article.model.Article;

import java.util.List;

/**
 * Created by wuhui on 2017/5/17.
 */

public interface ArticlePresenterInterface {
    void searchArticles(String channelId, String start, String num);
    List<Article> getArticles();
    public void deleteArticles();
}
