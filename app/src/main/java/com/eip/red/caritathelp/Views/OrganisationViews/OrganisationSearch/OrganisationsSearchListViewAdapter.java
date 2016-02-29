package com.eip.red.caritathelp.Views.OrganisationViews.OrganisationSearch;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Organisation;
import com.eip.red.caritathelp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pierr on 25/02/2016.
 */

public class OrganisationsSearchListViewAdapter extends BaseAdapter {

    private OrganisationSearchView  fragment;
    private List<Organisation>      organisations;

    public OrganisationsSearchListViewAdapter(OrganisationSearchView fragment) {
        this.fragment = fragment;
        organisations = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return (organisations.size());
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
        textView.setText(organisations.get(position).getName());

        return view;
    }

    public void setOrganisations(List<Organisation> organisations) {
        this.organisations = organisations;
    }
}

