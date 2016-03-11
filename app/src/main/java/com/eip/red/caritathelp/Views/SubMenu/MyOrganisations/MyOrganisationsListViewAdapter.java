package com.eip.red.caritathelp.Views.SubMenu.MyOrganisations;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Organisation;
import com.eip.red.caritathelp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 18/11/2015.
 */

public class MyOrganisationsListViewAdapter extends BaseAdapter {

    private MyOrganisationsView     fragment;
    private List<String>            myOrganisationsNames;

    public MyOrganisationsListViewAdapter(MyOrganisationsView fragment) {
        this.fragment = fragment;
        this.myOrganisationsNames = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return (myOrganisationsNames.size());
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
        TextView    textView = (TextView) view.findViewById(R.id.my_organisations_name);
        textView.setText(myOrganisationsNames.get(position));

        return view;
    }

    public void setMyOrganisationsNames(List<String> myOrganisationsNames) {
        this.myOrganisationsNames = myOrganisationsNames;
    }
}
