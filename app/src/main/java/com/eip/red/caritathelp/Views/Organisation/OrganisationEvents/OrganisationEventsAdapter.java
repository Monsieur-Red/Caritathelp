package com.eip.red.caritathelp.Views.Organisation.OrganisationEvents;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 17/03/2016.
 */
public class OrganisationEventsAdapter extends RecyclerView.Adapter<OrganisationEventsAdapter.DataObjectHolder> {

//    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView    title;
        TextView    date;

        public DataObjectHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.organisation_events_title);
            date = (TextView) itemView.findViewById(R.id.organisation_events_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
