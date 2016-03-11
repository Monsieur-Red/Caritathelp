package com.eip.red.caritathelp.Views.Organisation;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 18/02/2016.
 */

public class OrganisationListViewAdapter extends BaseAdapter {

    private OrganisationView fragment;

    public OrganisationListViewAdapter(OrganisationView fragment) {
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
            view = fragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_organisation_list_row, null);

        return view;
    }
}