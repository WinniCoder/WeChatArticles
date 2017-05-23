package com.wechatarticles.main.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;

/**
 * Created by wuhui on 2017/5/12.
 */


@Table(name = "Channels")
public class Channel extends Model{

    @Column(name = "channel_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("channelid")
    private String channelId;

    @Column(name = "channel_name")
    @SerializedName("channel")
    private String channelName;

    public transient Field field;

    public Channel() {
    }

    public String getChannelId() {
        return channelId;
    }

    public String getChannelName() {
        return channelName;
    }
}
