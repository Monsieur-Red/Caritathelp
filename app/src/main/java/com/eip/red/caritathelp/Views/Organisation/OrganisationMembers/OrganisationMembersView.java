package com.eip.red.caritathelp.Views.Organisation.OrganisationMembers;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Organisation.Member;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Presenters.Organisation.OrganisationMembers.OrganisationMembersPresenter;
import com.eip.red.caritathelp.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 25/02/2016.
 */

public class OrganisationMembersView extends Fragment implements IOrganisationMembersView {

    private OrganisationMembersPresenter    presenter;

    private ListView    listView;
    private EditText    searchBar;
    private ProgressBar progressBar;
    private AlertDialog dialog;

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
        int     organisationId = getArguments().getInt("organisation id");

        // Init Presenter
        presenter = new OrganisationMembersPresenter(this, network, organisationId);

        // Init Dialog
        dialog = new AlertDialog.Builder(getActivity().getBaseContext())
                .setCancelable(true)
                .create();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_organisation_members, container, false);

        // Set ToolBar
        ((MainActivity) getActivity()).getToolBar().update("Membres", true, false);

        // Init UI Element
        searchBar = (EditText) view.findViewById(R.id.organisation_members_search_text);
        progressBar = (ProgressBar) view.findViewById(R.id.organisation_members_progress_bar);

        // Init ListView & Listener & Adapter
        listView = (ListView)view.findViewById(R.id.organisation_members_list_view);
        listView.setAdapter(new OrganisationMembersListViewAdapter(this));
        initListViewListener();

        // Init Filter
        initSearchBarListener();

        // Init Members Model
        presenter.getMembers();

        return (view);
    }

    private void initListViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
/*
                // Go to organisation page
                presenter.goToOrganisationView((Organisation) parent.getItemAtPosition(position));

                // Init Text Search Bar
                searchBar.invalidate();
                searchBar.getText().clear();
                searchBar.setHint(R.string.organisations_search_bar);
*/
            }
        });
    }

    private void initSearchBarListener() {
        searchBar.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                String  text = searchBar.getText().toString().toLowerCase(Locale.getDefault());

                ((OrganisationMembersListViewAdapter) listView.getAdapter()).filter(text);
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        presenter.onClick(v.getId());
//    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDialogError(String title, String msg) {
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show();
    }

    @Override
    public void updateListView(List<Member> members) {
        ((OrganisationMembersListViewAdapter) listView.getAdapter()).setMembers(members);
        ((OrganisationMembersListViewAdapter) listView.getAdapter()).notifyDataSetChanged();
    }
}
