package com.eip.red.caritathelp.Views.OrganisationViews.Organisation.OrganisationMembers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 25/02/2016.
 */

public class OrganisationMembersListViewAdapter extends BaseAdapter {

    private OrganisationMembersView fragment;

    public OrganisationMembersListViewAdapter(OrganisationMembersView fragment) {
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return (10);
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
            view = fragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_organisation_members_list_row, null);

        // Set Organisation Name
//        TextView    textView = (TextView) view.findViewById(R.id.organisations_members_list_row_name);
//        textView.setText(organisations.get(position).getName());

        return view;
    }

}
