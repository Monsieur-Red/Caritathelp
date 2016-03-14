package com.eip.red.caritathelp.Views.OrganisationSearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation;
import com.eip.red.caritathelp.Presenters.OrganisationSearch.OrganisationSearchPresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;

import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 23/02/2016.
 */

public class OrganisationSearchView extends Fragment implements IOrganisationSearchView {

    private OrganisationSearchPresenter presenter;

    private View            view;
    private ListView        listView;
    private EditText        searchBar;
    private Button          cancel;
    private ProgressBar     progressBar;
    private AlertDialog     dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Model
        Network network = ((MainActivity) getActivity()).getModelManager().getNetwork();

        // Init Presenter
        presenter = new OrganisationSearchPresenter(this, network);

        // Init Dialog
        dialog = new AlertDialog.Builder(getContext())
                .setCancelable(true)
                .create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_organisation_search, container, false);

        // Init UI Element
        searchBar = (EditText) view.findViewById(R.id.organisations_search_text);
        cancel = (Button) view.findViewById(R.id.organisation_search_btn_cancel);
        progressBar = (ProgressBar) view.findViewById(R.id.organisation_search_progress_bar);

        // Init ListView & Listener & Adapter
        listView = (ListView) view.findViewById(R.id.organisations_search_list_view);
        listView.setAdapter(new OrganisationsSearchListViewAdapter(this));
        Tools.setListViewHeightBasedOnChildren(listView);
        initListViewListener();

        // Init SearchBar EditText listener
        initSearchBarListener();

        // Init TopBar listener
        initTopBarListener();

        // Get All Organisations
        presenter.getAllOrganisations();

        return (view);
    }

    private void initListViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Init Text Search Bar
                searchBar.invalidate();
                searchBar.getText().clear();
                searchBar.setHint(R.string.organisations_search_bar);

                // Go to organisation page
                presenter.goToOrganisationView((Organisation) listView.getItemAtPosition(position));
            }
        });
    }

    private void initSearchBarListener() {
        // Init Filter
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

                ((OrganisationsSearchListViewAdapter) listView.getAdapter()).filter(text);
            }
        });
    }

    private void initTopBarListener() {
        view.findViewById(R.id.organisations_search_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Cancel Btn
                cancel.setVisibility(View.VISIBLE);

                // Search Bar EditText Request Focus
                searchBar.requestFocus();

                // Show Keyboard
                Tools.showKeyboard(getContext(), searchBar);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide Btn
                cancel.setVisibility(View.GONE);

                // Search Bar EditText Clear Focus
                searchBar.clearFocus();

                // Hide Keyboard
                Tools.hideKeyboard(getContext(), view);
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
    public void updateListView(List<Organisation> organisations) {
        ((OrganisationsSearchListViewAdapter) listView.getAdapter()).setOrganisations(organisations);
        ((OrganisationsSearchListViewAdapter) listView.getAdapter()).notifyDataSetChanged();
        Tools.setListViewHeightBasedOnChildren(listView);
    }
}
