package com.wechatarticles.article.view;

import com.wechatarticles.article.model.Article;

import java.util.List;

/**
 * Created by wuhui on 2017/5/18.
 */

public interface ArticlesFragmentInterface {
    void presentArticles(List<Article> articles);
    void searchArticlesFail();
    void isBottom(int total,int num,int start);
}
