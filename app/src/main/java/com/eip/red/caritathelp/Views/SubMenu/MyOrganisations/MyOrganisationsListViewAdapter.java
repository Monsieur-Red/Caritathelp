package com.eip.red.caritathelp.Views.SubMenu.MyOrganisations;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Organisation;
import com.eip.red.caritathelp.R;

import java.util.List;

/**
 * Created by pierr on 18/11/2015.
 */

public class MyOrganisationsListViewAdapter extends BaseAdapter {

    private MyOrganisationsView     fragment;
    private List<Organisation>      organisations;

    public MyOrganisationsListViewAdapter(MyOrganisationsView fragment, List<Organisation> organisations) {
        this.fragment = fragment;
        this.organisations = organisations;
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
            view = fragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_my_organisations_list_row, null);

        // Set Organisation Name
        TextView    textView = (TextView) view.findViewById(R.id.my_organisations_title);
        textView.setText(organisations.get(position).getName());

        return view;
    }
}
