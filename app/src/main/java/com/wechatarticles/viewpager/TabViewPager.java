package com.wechatarticles.viewpager;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.wechatarticles.R;
import com.wechatarticles.main.model.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhui on 2017/5/8.
 */
public class TabViewPager extends LinearLayout implements ViewPager.OnPageChangeListener{
    private FragmentActivity context;
    private TabLayout tab;
    private List<Fragment> fragments;
    private List<Channel> titles;
    private ViewPager pager;

    public TabViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (context instanceof FragmentActivity){
            this.context=(FragmentActivity) context;
        }
        init();
    }

    private void init(){
        fragments=new ArrayList<>();
        titles=new ArrayList<>();
        pager=new ViewPager(context);
        //动态生成ViewPager时给它指定ID，否则会报错
        pager.setId("ADVP".hashCode());
        tab=new TabLayout(context);
        LayoutParams pagerLayoutParams=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        pager.setLayoutParams(pagerLayoutParams);
        LayoutParams tabLayoutParams=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tab.setLayoutParams(tabLayoutParams);
        setOrientation(VERTICAL);
        addView(tab);
        addView(pager);

        setTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        setSelectedTabIndicatorHeight(10);
        setTabTextColors(Color.BLACK,getResources().getColor(R.color.colorAccent));
        setTabMode(TabLayout.MODE_SCROLLABLE);
        setTabGravity(Gravity.CENTER);
    }

    public void addFragments(List<Fragment> fragmentList){
        fragments.addAll(fragmentList);
    }

    public void addTitles(List<Channel> titleList){
        titles.addAll(titleList);
    }

    public void setTabBackground(@ColorInt int color){
        tab.setBackgroundColor(color);
    }

    public void setTabIndicatorColor(@ColorInt int color){
        tab.setSelectedTabIndicatorColor(color);
    }

    public void setSelectedTabIndicatorHeight(int height){
        tab.setSelectedTabIndicatorHeight(height);
    }

    public void setTabTextColors(@ColorInt int normalColor, @ColorInt int selectedColor){
        tab.setTabTextColors(normalColor,selectedColor);
    }

    public void setTabPadding(int left, int top, int right, int bottom){
        tab.setPadding(left,top,right,bottom);
    }

    public void setTabMode(@TabLayout.Mode int mode){
        tab.setTabMode(mode);
    }

    public void setTabGravity(@TabLayout.TabGravity int gravity){
        tab.setTabGravity(gravity);
    }


    public void setAdapter(){
        ViewPagerAdapter adapter=new ViewPagerAdapter(context.getSupportFragmentManager(),fragments,titles);
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
