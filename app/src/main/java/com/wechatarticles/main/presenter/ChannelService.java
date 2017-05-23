package com.wechatarticles.main.presenter;

import com.wechatarticles.main.model.Channel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wuhui on 2017/5/12.
 */

public interface ChannelService {
    @GET("channel")
    Observable<ResponseBody> getChannels(@Query("appkey") String appkey);
}
