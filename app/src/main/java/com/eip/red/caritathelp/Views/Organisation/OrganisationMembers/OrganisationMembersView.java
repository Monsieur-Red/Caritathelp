package com.eip.red.caritathelp.Views.Organisation.OrganisationMembers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.eip.red.caritathelp.MainActivity.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation;
import com.eip.red.caritathelp.Presenters.Organisation.OrganisationPresenter;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 25/02/2016.
 */

public class OrganisationMembersView extends Fragment implements View.OnClickListener {

    private ListView    listView;

    public static OrganisationMembersView newInstance(int idOrganisation) {
        OrganisationMembersView    myFragment = new OrganisationMembersView();

        Bundle args = new Bundle();
        args.putInt("organisation id", idOrganisation);
        myFragment.setArguments(args);

        return (myFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Network Model & Id Organisation
        Network network = ((MainActivity) getActivity()).getModelManager().getNetwork();
        int     idOrganisation = getArguments().getInt("organisation id");

        // Init Presenter
//        presenter = new OrganisationPresenter(this, network, organisation);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_organisation_members, container, false);

        // Init ListView & Listener & Adapter
        listView = (ListView)view.findViewById(R.id.orga_list_view);
        listView.setAdapter(new OrganisationMembersListViewAdapter(this));
        initListener();

        // Init Button Listener
        view.findViewById(R.id.top_bar_organisation_members_return).setOnClickListener(this);


        return (view);
    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Page Change
//                TextView textView = (TextView) view.findViewById(R.id.my_organisations_title);

//                ((MainActivity) getActivity()).replaceView(OrganisationView.newInstance(textView.getText().toString()), true);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_organisation_members_return:
                ((MainActivity) getActivity()).goToPreviousPage();
                break;
        }
    }

}
