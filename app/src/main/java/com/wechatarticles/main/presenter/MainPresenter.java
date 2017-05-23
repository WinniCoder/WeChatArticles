package com.wechatarticles.main.presenter;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wechatarticles.common.Constant;
import com.wechatarticles.main.model.Channel;
import com.wechatarticles.main.view.MainActivityInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wuhui on 2017/5/12.
 */

public class MainPresenter implements MainPresenterInterface {
    private MainActivityInterface mainActivity;
    private ArrayList<Channel> channels;

    public MainPresenter(MainActivityInterface mainActivity) {
        this.mainActivity=mainActivity;
    }

    @Override
    public void searchChannels() {
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit=new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Constant.baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ChannelService channelService=retrofit.create(ChannelService.class);
        channelService.getChannels(Constant.APP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainActivity.searchChannelFail();
                        Log.e("wuhuierror","wuhuierror",e);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            JSONObject jsonObject=new JSONObject(responseBody.string());
                            if (jsonObject.getInt("status")!=0){
                                mainActivity.searchChannelFail();
                            } else {
                                Type channelListType=new TypeToken<ArrayList<Channel>>(){}.getType();
                                channels=Constant.gson.fromJson(jsonObject.getString("result"),channelListType);
                                mainActivity.searchChannelSuccess(channels);
                                saveChannels();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void saveChannels() {
        ActiveAndroid.beginTransaction();
        try {
            for (int i=0;i<channels.size();i++){
                channels.get(i).save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }finally {
            ActiveAndroid.endTransaction();
        }
    }

    @Override
    public List<Channel> getChannels() {
        return new Select()
                .from(Channel.class)
                .execute();
    }

    public static Channel getChannelName(String channelId) {
        return new Select()
                .from(Channel.class)
                .where("channel_id=?",channelId)
                .executeSingle();
    }


}
