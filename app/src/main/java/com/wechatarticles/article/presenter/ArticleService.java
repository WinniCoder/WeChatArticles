package com.wechatarticles.article.presenter;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wuhui on 2017/5/17.
 */


public interface ArticleService {
    @GET("get")
    Observable<ResponseBody> getArticles(@Query("channelid") String channelId,@Query("start") String start,@Query("num") String num,@Query("appkey") String appkey);
}
