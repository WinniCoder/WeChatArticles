package com.wechatarticles.main.view;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wechatarticles.R;
import com.wechatarticles.article.view.ArticlesFragment;
import com.wechatarticles.main.model.Channel;
import com.wechatarticles.main.presenter.MainPresenter;
import com.wechatarticles.main.presenter.MainPresenterInterface;
import com.wechatarticles.viewpager.TabViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {
    private MainPresenterInterface mainPresenter;
    private List<Channel> titles;
    private List<Fragment> fragments;

    @BindView(R.id.viewpager)
    TabViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);
        init();
    }

    public void init(){
        titles=mainPresenter.getChannels();
        fragments = new ArrayList<>();
        if (titles.size()==0){
            mainPresenter.searchChannels();
        } else {
            initViewPager();
        }
    }

    void initViewPager(){
        for (int i = 0; i < titles.size(); i++) {
            Fragment fragment=new ArticlesFragment();
            Bundle bundle=new Bundle();
            bundle.putString("channel_id",titles.get(i).getChannelId());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        viewPager.addTitles(titles);
        viewPager.addFragments(fragments);
        viewPager.setAdapter();
    }


    @Override
    public void searchChannelSuccess(List<Channel> channels) {
        titles.addAll(channels);
        initViewPager();
    }

    @Override
    public void searchChannelFail() {
        Toast.makeText(this, "网络连接失败", Toast.LENGTH_SHORT).show();
    }
}
