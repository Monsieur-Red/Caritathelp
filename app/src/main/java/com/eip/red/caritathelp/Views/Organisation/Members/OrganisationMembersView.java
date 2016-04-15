package com.eip.red.caritathelp.Views.Organisation.Members;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Activities.Main.MySearchBar;
import com.eip.red.caritathelp.Models.Organisation.Member;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Presenters.Organisation.Members.OrganisationMembersPresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;
import com.eip.red.caritathelp.Views.Organisation.Events.OrganisationEventsRVAdapter;

import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 25/02/2016.
 */

public class OrganisationMembersView extends Fragment implements IOrganisationMembersView {

    private OrganisationMembersPresenter    presenter;

    private ListView    listView;
    private ProgressBar progressBar;
    private AlertDialog dialog;

    public static OrganisationMembersView newInstance(int idOrganisation) {
        OrganisationMembersView    myFragment = new OrganisationMembersView();

        Bundle args = new Bundle();
        args.putInt("page", R.string.view_name_organisation_members);
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
        dialog = new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .create();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_organisation_members, container, false);

        // Set ToolBar
//        ((MainActivity) getActivity()).getToolBar().update("Membres", true);

        // Init SearchBar
//        initSearchBar();

        // Init UI Element
        progressBar = (ProgressBar) view.findViewById(R.id.organisation_members_progress_bar);

        // Init ListView & Listener & Adapter
        listView = (ListView)view.findViewById(R.id.organisation_members_list_view);
        listView.setAdapter(new OrganisationMembersListViewAdapter(this));
        initListViewListener();

        return (view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init ToolBar Title
        getActivity().setTitle(getArguments().getInt("page"));

        // Init Members Model
        presenter.getMembers();
    }

/*
    private void initSearchBar() {
        MySearchBar searchBar = ((MainActivity) getActivity()).getToolBar().getSearchBar();
        final EditText    searchText = searchBar.getSearchText();
        final ImageButton cancelBtn = searchBar.getCancelBtn();

        // Show SearchBar
        searchBar.setVisibility(View.VISIBLE);

        // Show the SearchBar
        searchBar.show(R.string.search_bar_member);

        //Init SearchText listener & filter
        searchText.addTextChangedListener(new TextWatcher() {

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
                if (TextUtils.isEmpty(arg0)) {
                    // Hide Cancel Btn
                    cancelBtn.setVisibility(View.GONE);

                    // Flush Filter
//                    ((OrganisationEventsRVAdapter) recyclerView.getAdapter()).flushFilter();
                }
                else {
                    // Show Cancel Btn
                    cancelBtn.setVisibility(View.VISIBLE);

                    // Filter text
                    String text = searchText.getText().toString().toLowerCase(Locale.getDefault());
                    ((OrganisationMembersListViewAdapter) listView.getAdapter()).filter(text);
                }
            }
        });
    }
*/

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
        ((OrganisationMembersListViewAdapter) listView.getAdapter()).update(members);
    }
}
