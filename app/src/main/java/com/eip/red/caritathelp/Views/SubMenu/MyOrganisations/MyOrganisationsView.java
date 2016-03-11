package com.eip.red.caritathelp.Views.SubMenu.MyOrganisations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eip.red.caritathelp.MainActivity.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations.MyOrganisationsPresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Organisation.OrganisationView;
import com.eip.red.caritathelp.Views.OrganisationSearch.OrganisationsSearchListViewAdapter;
import com.eip.red.caritathelp.Views.SubMenu.MyOrganisations.OrganisationCreation.OrganisationCreationView;

import java.util.List;

/**
 * Created by pierr on 16/11/2015.
 */
public class MyOrganisationsView extends Fragment implements IMyOrganisationsView, View.OnClickListener{

    private MyOrganisationsPresenter    presenter;

    private ListView        listView;
    private ProgressBar     progressBar;
    private AlertDialog     dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Model
        User    user = ((MainActivity) getActivity()).getModelManager().getUser();
        Network network = ((MainActivity) getActivity()).getModelManager().getNetwork();

        // Init Presenter
        presenter = new MyOrganisationsPresenter(this, user, network);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_my_organisations, container, false);

        // Init UI Element
        progressBar = (ProgressBar)  view.findViewById(R.id.my_organisations_progress_bar);

        // Init ListView & Listener & Adapter
        listView = (ListView)view.findViewById(R.id.orga_list_view);
        listView.setAdapter(new MyOrganisationsListViewAdapter(this));
        initListener();

        // Init Button Listener
        view.findViewById(R.id.top_bar_my_organisations_return).setOnClickListener(this);
        view.findViewById(R.id.top_bar_my_organisations_btn_add_orga).setOnClickListener(this);

        // Init MyOrganisation Model by requesting the api
        presenter.getMyOrganisations();

        // Init Dialog
        dialog = new AlertDialog.Builder(getContext())
                .setCancelable(true)
                .create();

        return (view);
    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Page Change
                String          orgaName = ((TextView) view.findViewById(R.id.my_organisations_name)).getText().toString();

                presenter.goToOrganisationView(orgaName);
            }
        });
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
    public void updateListView(List<String> myOrganisationsNames) {
        ((MyOrganisationsListViewAdapter) listView.getAdapter()).setMyOrganisationsNames(myOrganisationsNames);
        ((MyOrganisationsListViewAdapter) listView.getAdapter()).notifyDataSetChanged();
    }

}
