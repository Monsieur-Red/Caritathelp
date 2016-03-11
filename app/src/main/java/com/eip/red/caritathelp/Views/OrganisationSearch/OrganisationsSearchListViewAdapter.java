package com.eip.red.caritathelp.Views.OrganisationSearch;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eip.red.caritathelp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pierr on 25/02/2016.
 */

public class OrganisationsSearchListViewAdapter extends BaseAdapter {

    private OrganisationSearchView  fragment;
    private List<String>            organisationsNames;

    public OrganisationsSearchListViewAdapter(OrganisationSearchView fragment) {
        this.fragment = fragment;
        organisationsNames = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return (organisationsNames.size());
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
            view = fragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_organisations_search_list_row, null);

        // Set Organisation Name
        TextView textView = (TextView) view.findViewById(R.id.organisations_search_name);
        textView.setText(organisationsNames.get(position));

        return view;
    }

    public void setOrganisationsNames(List<String> organisationsNames) {
        this.organisationsNames = organisationsNames;
    }
}

