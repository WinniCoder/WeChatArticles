package com.wechatarticles.article.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.lang.reflect.Field;

/**
 * Created by wuhui on 2017/5/19.
 */

@Table(name = "Articles")
public class Article extends Model{
    @Column(name = "channelid")
    String channelid;
    @Column(name = "title")
    String title; 	//标题
    @Column(name = "time")
    String time;	//时间
    @Column(name = "weixinname")
    String weixinname; 	//微信公众号名称
    @Column(name = "weixinaccount")
    String weixinaccount; 	//微信号
    @Column(name = "weixinsummary")
    String weixinsummary; 	 //微信公众号介绍
    @Column(name = "pic")
    String pic;	//图片
    @Column(name = "content")
    String content;	//内容
    @Column(name = "url")
    String url; 	//文章微信网址
    @Column(name = "readnum")
    String readnum; 	//阅读数
    @Column(name = "likenum")
    String likenum; 	//点赞数

    public transient Field field;

    public String getChannelid() {
        return channelid;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getWeixinname() {
        return weixinname;
    }

    public String getWeixinaccount() {
        return weixinaccount;
    }

    public String getWeixinsummary() {
        return weixinsummary;
    }

    public String getPic() {
        return pic;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getReadnum() {
        return readnum;
    }

    public String getLikenum() {
        return likenum;
    }
}
