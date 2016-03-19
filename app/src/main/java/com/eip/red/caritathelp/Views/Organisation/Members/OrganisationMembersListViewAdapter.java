package com.eip.red.caritathelp.Views.Organisation.Members;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Organisation.Member;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 25/02/2016.
 */

public class OrganisationMembersListViewAdapter extends BaseAdapter {

    private OrganisationMembersView fragment;
    private List<Member>            visibleObjects;
    private List<Member>            allObjects;


    public OrganisationMembersListViewAdapter(OrganisationMembersView fragment) {
        this.fragment = fragment;
        visibleObjects = new ArrayList<>();
        allObjects = new ArrayList<>();
    }

    private class ViewHolder {
        TextView    memberName;
    }

    @Override
    public int getCount() {
        return (visibleObjects.size());
    }

    @Override
    public Object getItem(int position) {
        return (visibleObjects.get(position));
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
            view = fragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_organisation_members_list_row, null);
            holder.memberName = (TextView) view.findViewById(R.id.organisation_members_list_row_name);
            view.setTag(holder);
        }
        else
            holder = (ViewHolder) view.getTag();

        // Set Members Name
        Member      member = visibleObjects.get(position);
        String      name = Tools.upperCaseFirstLetter(member.getFirstname()) + " " + Tools.upperCaseFirstLetter(member.getLastname());
        holder.memberName.setText(name);

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        visibleObjects.clear();
        if (charText.length() == 0)
            visibleObjects.addAll(allObjects);
        else {
            for (Member member : allObjects) {
                String name = member.getFirstname() + " " + member.getLastname();
                if (name.toLowerCase(Locale.getDefault()).contains(charText))
                    visibleObjects.add(member);
            }
        }
        notifyDataSetChanged();
    }

    public void update(List<Member> members) {
        allObjects.clear();
        visibleObjects.clear();

        allObjects.addAll(members);
        visibleObjects.addAll(members);

        notifyDataSetChanged();
    }
}
