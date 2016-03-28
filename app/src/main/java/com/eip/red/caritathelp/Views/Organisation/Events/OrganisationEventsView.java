package com.eip.red.caritathelp.Views.Organisation.Events;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Activities.Main.MySearchBar;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.MyWidgets.DividerItemDecoration;
import com.eip.red.caritathelp.Presenters.Organisation.Events.OrganisationEventsPresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.SubMenu.MyEvents.MyEventsRVAdapter;

import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 17/03/2016.
 */

public class OrganisationEventsView extends Fragment implements IOrganisationEventsView {

    private OrganisationEventsPresenter presenter;

    private RecyclerView    recyclerView;
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
        ((MainActivity) getActivity()).getToolBar().update("Événements", true);

        // Init SearchBar
        initSearchBar();

        // Init UI Element
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Init RecyclerView
        initRecyclerView(view);

        // Init Events Model
        presenter.getEvents();

        return (view);
    }

    private void initSearchBar() {
        MySearchBar searchBar = ((MainActivity) getActivity()).getToolBar().getSearchBar();
        final EditText    searchText = searchBar.getSearchText();
        final ImageButton cancelBtn = searchBar.getCancelBtn();

        // Show the SearchBar
        searchBar.show(R.string.search_bar_event);

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
                    ((OrganisationEventsRVAdapter) recyclerView.getAdapter()).flushFilter();
                }
                else {
                    // Show Cancel Btn
                    cancelBtn.setVisibility(View.VISIBLE);

                    // Filter text
                    String text = searchText.getText().toString().toLowerCase(Locale.getDefault());
                    ((OrganisationEventsRVAdapter) recyclerView.getAdapter()).filter(text);
                }
            }
        });
    }

    private void initRecyclerView(View view) {
        // Init RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new OrganisationEventsRVAdapter(presenter));

        // Init LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        // Init Divider (between items)
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this.getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
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
