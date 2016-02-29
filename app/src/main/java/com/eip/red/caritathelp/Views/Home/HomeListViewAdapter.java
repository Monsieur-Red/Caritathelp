package com.eip.red.caritathelp.Views.Home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Home.News;
import com.eip.red.caritathelp.Models.Home.NewsList;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 16/11/2015.
 */

public class HomeListViewAdapter extends BaseAdapter {

    private HomeView    fragment;
    private NewsList    newsList;

    public HomeListViewAdapter(HomeView fragment, NewsList newsList) {
        this.fragment = fragment;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return (newsList.getNews().size());
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View    view = convertView;

        if (convertView == null)
            view = fragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_home_list_row, null);

        News        news = newsList.getNews(position);
        ImageView   logo = (ImageView) view.findViewById(R.id.news_image);
        TextView    title = (TextView) view.findViewById(R.id.news_title);
        TextView    date = (TextView) view.findViewById(R.id.news_date);
        TextView    content = (TextView) view.findViewById(R.id.news_description);

        logo.setImageDrawable(view.getResources().getDrawable(news.getLogo()));
        title.setText(news.getTitle());
        date.setText(news.getDate());
        content.setText(news.getContent());

        return view;
    }
}
