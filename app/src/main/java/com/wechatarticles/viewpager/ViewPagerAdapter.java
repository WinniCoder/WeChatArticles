package com.wechatarticles.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wechatarticles.main.model.Channel;

import java.util.List;

/**
 * Created by wuhui on 2017/5/8.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<Channel> titles;

    public ViewPagerAdapter(FragmentManager fm,List<Fragment> fragments,List<Channel> titles) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getChannelName();
    }

}
