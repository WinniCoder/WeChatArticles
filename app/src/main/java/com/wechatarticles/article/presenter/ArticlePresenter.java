package com.wechatarticles.article.presenter;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.reflect.TypeToken;
import com.wechatarticles.article.model.Article;
import com.wechatarticles.article.view.ArticlesFragmentInterface;
import com.wechatarticles.common.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wuhui on 2017/5/19.
 */

public class ArticlePresenter implements ArticlePresenterInterface {
    private ArticlesFragmentInterface articlesFragment;
    private List<Article> articles;

    public ArticlePresenter(ArticlesFragmentInterface articlesFragment) {
        this.articlesFragment=articlesFragment;
    }

    @Override
    public void searchArticles(String channelId, String start, String num) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ArticleService articleService=retrofit.create(ArticleService.class);
        articleService.getArticles(channelId,start,num,Constant.APP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        articlesFragment.searchArticlesFail();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            JSONObject jsonObject=new JSONObject(responseBody.string());
                            if (!jsonObject.getString("status").equals("0")){
                                articlesFragment.searchArticlesFail();
                                Log.e("wuhuierror",jsonObject.getString("msg"));
                            } else {
                                Type articleListType=new TypeToken<ArrayList<Article>>(){}.getType();
                                JSONObject result=jsonObject.getJSONObject("result");
                                articles=Constant.gson.fromJson(result.getString("list"),articleListType);
                                articlesFragment.presentArticles(articles);
                                articlesFragment.isBottom(Integer.parseInt(result.getString("total")),Integer.parseInt(result.getString("num")),Integer.parseInt(result.getString("start")));
                                saveArticles();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
     public List<Article> getArticles() {
        return new Select().from(Article.class).execute();
    }

    public void deleteArticles(){
        new Delete().from(Article.class).execute();
    }

    public void saveArticles(){
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < articles.size(); i++) {
                articles.get(i).save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }
}
