package com.eip.red.caritathelp.Views.SubMenu.MyOrganisations;

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
 * Created by pierr on 18/11/2015.
 */

public class MyOrganisationsListViewAdapter extends BaseAdapter {

    private MyOrganisationsView     fragment;
    private List<Organisation>      myOrganisationsFilter;
    private List<Organisation>      myOrganisations;

    public MyOrganisationsListViewAdapter(MyOrganisationsView fragment) {
        this.fragment = fragment;
        this.myOrganisationsFilter = new ArrayList<>();
        this.myOrganisations = new ArrayList<>();
    }

    private class ViewHolder {
        TextView    organisationName;
    }

    @Override
    public int getCount() {
        return (myOrganisationsFilter.size());
    }

    @Override
    public Object getItem(int position) {
        return (myOrganisationsFilter.get(position));
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
            view = fragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_submenu_my_organisations_list_row, null);
            holder.organisationName = (TextView) view.findViewById(R.id.my_organisations_name);
            view.setTag(holder);
        }
        else
            holder = (ViewHolder) view.getTag();

        // Set Organisation Name
        holder.organisationName.setText(Tools.upperCaseFirstLetter(myOrganisationsFilter.get(position).getName()));

        return (view);
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        myOrganisationsFilter.clear();
        if (charText.length() == 0)
            myOrganisationsFilter.addAll(myOrganisations);
        else {
            for (Organisation organisation : myOrganisations) {
                String name = organisation.getName();
                if (name.toLowerCase(Locale.getDefault()).contains(charText))
                    myOrganisationsFilter.add(organisation);
            }
        }
        notifyDataSetChanged();
    }

    public void setMyOrganisations(List<Organisation> myOrganisations) {
        this.myOrganisationsFilter.clear();
        this.myOrganisations.clear();

        this.myOrganisationsFilter.addAll(myOrganisations);
        this.myOrganisations.addAll(myOrganisations);
    }
}
