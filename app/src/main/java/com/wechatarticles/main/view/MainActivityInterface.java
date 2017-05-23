package com.wechatarticles.main.view;

import android.app.Fragment;

import com.wechatarticles.main.model.Channel;

import java.util.List;

/**
 * Created by wuhui on 2017/5/12.
 */

public interface MainActivityInterface {
    void searchChannelSuccess(List<Channel> channels);
    void searchChannelFail();
}
