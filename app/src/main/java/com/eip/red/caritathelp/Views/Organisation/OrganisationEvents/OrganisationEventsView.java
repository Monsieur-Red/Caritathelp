package com.eip.red.caritathelp.Views.Organisation.OrganisationEvents;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Presenters.Organisation.OrganisationMembers.OrganisationMembersPresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Organisation.OrganisationMembers.OrganisationMembersListViewAdapter;

/**
 * Created by pierr on 17/03/2016.
 */

public class OrganisationEventsView extends Fragment {

//    private OrganisationEventsPresenter    presenter;

    private RecyclerView    recyclerView;
    private EditText        searchBar;
    private ProgressBar     progressBar;
    private AlertDialog     dialog;


    public static OrganisationEventsView newInstance(int idOrganisation) {
        OrganisationEventsView    myFragment = new OrganisationEventsView();

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
        int     organisationId = getArguments().getInt("organisation id");

        // Init Presenter
//        presenter = new OrganisationEventsPresenter(this, network, organisationId);

        // Init Dialog
        dialog = new AlertDialog.Builder(getActivity().getBaseContext())
                .setCancelable(true)
                .create();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_organisation_events, container, false);

        // Set ToolBar
        ((MainActivity) getActivity()).getToolBar().update("Événement", true, false);

        // Init UI Element
        searchBar = (EditText) view.findViewById(R.id.search_text);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Init ListView & Listener & Adapter
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new OrganisationEventsAdapter());
//        listView = (ListView)view.findViewById(R.id.organisation_members_list_view);
//        listView.setAdapter(new OrganisationMembersListViewAdapter(this));
//        initListViewListener();

        // Init Filter
//        initSearchBarListener();

        // Init Members Model
//        presenter.getMembers();

        return (view);
    }

    private void initRecyclerViewListener() {
//        ((OrganisationEventsAdapter)recyclerView).setOnIt
    }

}
