package com.eip.red.caritathelp.Views.MyEvents;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 18/11/2015.
 */

public class EventsListViewAdapter extends BaseAdapter {

    private MyEventsView fragment;

    public EventsListViewAdapter(MyEventsView fragment) {
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return 10;
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
        View view = convertView;

        if (convertView == null)
            view = fragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_events_list_row, null);

        return view;
    }
}
