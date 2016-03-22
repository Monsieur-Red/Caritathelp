package com.eip.red.caritathelp.Views.Organisation.Events;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.Presenters.Organisation.Events.OrganisationEventsPresenter;
import com.eip.red.caritathelp.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 17/03/2016.
 */

public class OrganisationEventsView extends Fragment implements IOrganisationEventsView {

    private OrganisationEventsPresenter presenter;

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
        presenter = new OrganisationEventsPresenter(this, network, organisationId);

        // Init Dialog
        dialog = new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .create();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_organisation_events, container, false);

        // Set ToolBar
        ((MainActivity) getActivity()).getToolBar().update("Événements", true, false);

        // Init UI Element
        searchBar = (EditText) view.findViewById(R.id.search_text);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Init RecyclerView & Listener & Adapter
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new OrganisationEventsRVAdapter(presenter));

        // Init LayoutManager
        LinearLayoutManager llayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(llayoutManager);

        // Init Filter
        initSearchBarListener();

        // Init Events Model
        presenter.getEvents();

        return (view);
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
                if (TextUtils.isEmpty(arg0))
                    ((OrganisationEventsRVAdapter) recyclerView.getAdapter()).flushFilter();
                else {
                    String text = searchBar.getText().toString().toLowerCase(Locale.getDefault());
                    ((OrganisationEventsRVAdapter) recyclerView.getAdapter()).filter(text);
                }
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
    public void updateRecyclerView(List<Event> events) {
        ((OrganisationEventsRVAdapter) recyclerView.getAdapter()).update(events);
    }
}
