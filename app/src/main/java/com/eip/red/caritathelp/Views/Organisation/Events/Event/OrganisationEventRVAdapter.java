package com.eip.red.caritathelp.Views.Organisation.Events.Event;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Home.News;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 18/03/2016.
 */
public class OrganisationEventRVAdapter extends RecyclerView.Adapter<OrganisationEventRVAdapter.EventObjectHolder> {

//    private List<Event> visibleObjects;
//    private List<Event> allObjects;

//    private static MyClickListener myClickListener;

    public OrganisationEventRVAdapter() {
//        visibleObjects = new ArrayList<>();
//        allObjects = new ArrayList<>();
    }

    public static class EventObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView   image;
        TextView    title;
        TextView    date;
        TextView    description;

        public EventObjectHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.news_image);
            title = (TextView) itemView.findViewById(R.id.news_title);
            date = (TextView) itemView.findViewById(R.id.news_date);
            description = (TextView) itemView.findViewById(R.id.news_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }


    @Override
    public EventObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_organisation_event_recycler_view_row, parent, false);
        EventObjectHolder   holder = new EventObjectHolder(view);

        return (holder);
    }

    @Override
    public void onBindViewHolder(EventObjectHolder holder, int position) {
//        holder.image.setText();
//        holder.title.setText(visibleObjects.get(position).getTitle());
//        holder.date.setText();
    }

    @Override
    public int getItemCount() {
        return (10);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void update(List<News> news) {
//        visibleObjects.clear();
//        allObjects.clear();
//
//        visibleObjects.addAll(events);
//        allObjects.addAll(events);
//
//        notifyDataSetChanged();
    }
}
