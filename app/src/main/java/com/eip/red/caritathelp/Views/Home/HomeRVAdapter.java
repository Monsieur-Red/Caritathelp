package com.eip.red.caritathelp.Views.Home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Home.News;
import com.eip.red.caritathelp.Models.Home.NewsList;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 05/04/2016.
 */

public class HomeRVAdapter extends RecyclerView.Adapter<HomeRVAdapter.DataObjectHolder> {

    private NewsList    newsList;

    public HomeRVAdapter(NewsList newsList) {
        this.newsList = newsList;
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView   image;
        TextView    title;
        TextView    date;
        TextView    description;

        public DataObjectHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.news_image);
            title = (TextView) itemView.findViewById(R.id.news_title);
            date = (TextView) itemView.findViewById(R.id.news_date);
            description = (TextView) itemView.findViewById(R.id.news_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Event event = visibleObjects.get(getAdapterPosition());
//
//            if (event != null)
//                presenter.navigateToEventView(event);
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_rv_row, parent, false);
        DataObjectHolder    holder = new DataObjectHolder(view);

        return (holder);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        News    news = newsList.getNews(position);

        if (news != null) {
            holder.image.setImageResource(news.getLogo());
            holder.title.setText(news.getTitle());
            holder.date.setText(news.getDate());
            holder.description.setText(news.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return (newsList.getNews().size());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void update() {
        notifyDataSetChanged();
    }
}
