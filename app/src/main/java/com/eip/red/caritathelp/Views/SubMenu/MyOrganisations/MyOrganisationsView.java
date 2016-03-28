package com.eip.red.caritathelp.Views.SubMenu.MyOrganisations;

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
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Activities.Main.MySearchBar;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.MyWidgets.DividerItemDecoration;
import com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations.MyOrganisationsPresenter;
import com.eip.red.caritathelp.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 16/11/2015.
 */
public class MyOrganisationsView extends Fragment implements IMyOrganisationsView, View.OnClickListener{

    private MyOrganisationsPresenter    presenter;

    private RecyclerView                recyclerViewOwner;
    private RecyclerView                recyclerViewMember;
    private MyOrganisationsRVAdapter    adapterOwner;
    private MyOrganisationsRVAdapter    adapterMember;
    private ProgressBar                 progressBar;
    private AlertDialog                 dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Model
        User    user = ((MainActivity) getActivity()).getModelManager().getUser();
        Network network = ((MainActivity) getActivity()).getModelManager().getNetwork();

        // Init Presenter
        presenter = new MyOrganisationsPresenter(this, user, network);

        // Init Dialog
        dialog = new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .create();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_submenu_my_organisations, container, false);

        // Set ToolBar
        ((MainActivity) getActivity()).getToolBar().update("Mes associations", true);

        // Init SearchBar
        initSearchBar();

        // Init UI Element
        progressBar = (ProgressBar)  view.findViewById(R.id.progress_bar);

        // Init RecyclerView
        initRecyclerViews(view);

        // Init Button Listener
        view.findViewById(R.id.btn_create).setOnClickListener(this);

        // Init MyOrganisation Model by requesting the api
        presenter.getMyOrganisations();

        return (view);
    }

    private void initSearchBar() {
        MySearchBar searchBar = ((MainActivity) getActivity()).getToolBar().getSearchBar();
        final EditText    searchText = searchBar.getSearchText();
        final ImageButton cancelBtn = searchBar.getCancelBtn();

        // Show SearchBar
        searchBar.setVisibility(View.VISIBLE);

        // Show the SearchBar
        searchBar.show(R.string.search_bar_organisation);

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
                    adapterOwner.flushFilter();
                    adapterMember.flushFilter();
                }
                else {
                    // Show Cancel Btn
                    cancelBtn.setVisibility(View.VISIBLE);

                    // Filter text
                    String text = searchText.getText().toString().toLowerCase(Locale.getDefault());
                    adapterOwner.filter(text);
                    adapterMember.filter(text);
                }
            }
        });
    }

    private void initRecyclerViews(View view) {
        // Init Recycler Views
        recyclerViewOwner = (RecyclerView) view.findViewById(R.id.my_organisations_owner_profile_recycler_view);
        recyclerViewMember = (RecyclerView) view.findViewById(R.id.my_organisations_member_profile_recycler_view);

        // Init Adapter
        adapterOwner = new MyOrganisationsRVAdapter(presenter);
        adapterMember = new MyOrganisationsRVAdapter(presenter);

        // Set Adapter
        recyclerViewOwner.setAdapter(adapterOwner);
        recyclerViewMember.setAdapter(adapterMember);

        // Init LayoutManager
        recyclerViewOwner.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerViewMember.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        // Set Options to enable toolbar display/hide
        recyclerViewOwner.setNestedScrollingEnabled(false);
        recyclerViewMember.setNestedScrollingEnabled(false);
        recyclerViewOwner.setHasFixedSize(false);
        recyclerViewMember.setHasFixedSize(false);

        // Init Divider (between items)
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this.getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL);
        recyclerViewOwner.addItemDecoration(itemDecoration);
        recyclerViewMember.addItemDecoration(itemDecoration);
    }

    @Override
    public void onClick(View v) {
        presenter.onClick(v.getId());
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
    public void updateListView(List<Organisation> myOrganisationsOwner, List<Organisation> myOrganisationsMember) {
        adapterOwner.update(myOrganisationsOwner);
        adapterMember.update(myOrganisationsMember);
    }
}
