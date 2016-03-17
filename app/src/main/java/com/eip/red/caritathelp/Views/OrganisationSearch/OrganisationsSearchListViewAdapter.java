package com.eip.red.caritathelp.Views.OrganisationSearch;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by pierr on 25/02/2016.
 */

public class OrganisationsSearchListViewAdapter extends BaseAdapter {

    private OrganisationSearchView  fragment;
    private List<Organisation>      organisationsFilter;
    private List<Organisation>      organisations;

    public OrganisationsSearchListViewAdapter(OrganisationSearchView fragment) {
        this.fragment = fragment;
        organisationsFilter = new ArrayList<>();
        organisations = new ArrayList<>();
    }

    private class ViewHolder {
        TextView    organisationName;
    }

    @Override
    public int getCount() {
        return (organisationsFilter.size());
    }

    @Override
    public Object getItem(int position) {
        return (organisationsFilter.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = fragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_organisations_search_list_row, null);
            holder.organisationName = (TextView) view.findViewById(R.id.organisations_search_name);
            view.setTag(holder);
        }
        else
            holder = (ViewHolder) view.getTag();

        // Set Organisation Name
        holder.organisationName.setText(Tools.upperCaseFirstLetter(organisationsFilter.get(position).getName()));

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        organisationsFilter.clear();
        if (charText.length() == 0)
            organisationsFilter.addAll(organisations);
        else {
            for (Organisation organisation : organisations) {
                String name = organisation.getName();
                if (name.toLowerCase(Locale.getDefault()).contains(charText))
                    organisationsFilter.add(organisation);
            }
        }
        notifyDataSetChanged();
    }


    public void setOrganisations(List<Organisation> organisations) {
        this.organisationsFilter.clear();
        this.organisations.clear();

        this.organisationsFilter.addAll(organisations);
        this.organisations.addAll(organisations);
    }
}

