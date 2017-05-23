package com.wechatarticles.common;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wechatarticles.R;
import com.wechatarticles.article.model.Article;
import com.wechatarticles.article.view.ArticleResourceActiviry;
import com.wechatarticles.main.presenter.MainPresenter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuhui on 2017/5/21.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder>{
    private Context context;
    private List<Article> articles;

    public ArticlesAdapter(Context context, List<Article> articles) {
        this.context=context;
        this.articles=articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.article_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Article article=articles.get(position);
        holder.channelResource.setText(MainPresenter.getChannelName(article.getChannelid()).getChannelName());
        holder.title.setText(article.getTitle());
        holder.weixinName.setText(article.getWeixinname());
        holder.weixinSummary.setText(article.getWeixinsummary());

        //过滤html标签
        String regEx_html = "<[^>]+>";
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        String htmlStr = article.getContent();
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");

        holder.content.setText(htmlStr);
        holder.readNum.setText(article.getReadnum());
        holder.likeNum.setText(article.getLikenum());
        holder.time.setText(article.getTime());

        Glide.with(context).load(article.getPic()).placeholder(R.drawable.load_before).error(R.drawable.load_fail).into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ArticleResourceActiviry.class);
                intent.putExtra("url",article.getUrl());
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.channel_resource)
        TextView channelResource;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.weixinname)
        TextView weixinName;
        @BindView(R.id.weixinsummary)
        TextView weixinSummary;
        @BindView(R.id.pic)
        ImageView pic;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.read_num)
        TextView readNum;
        @BindView(R.id.like_num)
        TextView likeNum;
        @BindView(R.id.time)
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}