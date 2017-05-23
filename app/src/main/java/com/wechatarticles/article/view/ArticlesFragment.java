package com.wechatarticles.article.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wechatarticles.R;
import com.wechatarticles.article.model.Article;
import com.wechatarticles.article.presenter.ArticlePresenter;
import com.wechatarticles.article.presenter.ArticlePresenterInterface;
import com.wechatarticles.common.ArticlesAdapter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuhui on 2017/5/13.
 */

public class ArticlesFragment extends Fragment implements ArticlesFragmentInterface {
    private String channelId;
    private ArticlesAdapter adapter;
    private List<Article> articles;
    private ArticlePresenterInterface articlePresenter;
    private int flag = 0;

    @BindView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        ButterKnife.bind(this,view);

        Bundle bundle = getArguments();
        channelId = bundle.getString("channel_id");

        articlePresenter = new ArticlePresenter(this);

        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        recyclerView.setLinearLayout();
        articles = articlePresenter.getArticles();
        adapter = new ArticlesAdapter(getContext(), articles);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ArticlesDivider(getContext()));
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                flag = 0;
                articlePresenter.searchArticles(channelId, Integer.toString(0), Integer.toString(10));
            }

            @Override
            public void onLoadMore() {
                flag = 1;
                articlePresenter.searchArticles(channelId, Integer.toString(articles.size()), Integer.toString(10));
            }
        });

        recyclerView.setRefreshing(true);
        articlePresenter.searchArticles(channelId, Integer.toString(0), Integer.toString(10));
    }

    @Override
    public void presentArticles(List<Article> articleList) {
        if (flag == 0) {
            articles.clear();
        }
        articles.addAll(articleList);
        adapter.notifyDataSetChanged();
        recyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void searchArticlesFail() {
        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
        recyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void isBottom(int total, int num, int start) {
        if (start+num>=total){
            recyclerView.setPushRefreshEnable(false);
        } else {
            recyclerView.setPushRefreshEnable(true);
        }
    }

}
