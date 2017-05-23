package com.wechatarticles.main.presenter;

import com.wechatarticles.main.model.Channel;

import java.util.List;

/**
 * Created by wuhui on 2017/5/12.
 */

public interface MainPresenterInterface {
    void searchChannels();
    List<Channel> getChannels();
}
