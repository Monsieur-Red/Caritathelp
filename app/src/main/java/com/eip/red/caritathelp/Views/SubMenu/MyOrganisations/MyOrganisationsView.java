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
import com.eip.red.caritathelp.Views.OrganisationViews.Organisation.OrganisationView;
import com.eip.red.caritathelp.Views.SubMenu.MyOrganisations.OrganisationCreation.OrganisationCreationView;

/**
 * Created by pierr on 16/11/2015.
 */
public class MyOrganisationsView extends Fragment implements IMyOrganisationsView, View.OnClickListener{

    private MyOrganisationsPresenter    presenter;

    private OrganisationCreationView    organisationCreationView;
    private OrganisationView            organisationView;

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

        // Init View
        organisationCreationView = new OrganisationCreationView();
        organisationView = new OrganisationView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_my_organisations, container, false);

        // Init UI Element
        progressBar = (ProgressBar)  view.findViewById(R.id.my_organisations_progress_bar);

        // Init ListView & Listener & Adapter
        listView = (ListView)view.findViewById(R.id.orga_list_view);
        listView.setAdapter(new MyOrganisationsListViewAdapter(this, ((MainActivity) getActivity()).getModelManager().getUser().getOrganisations()));
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
                TextView textView = (TextView) view.findViewById(R.id.my_organisations_title);

                ((MainActivity) getActivity()).replaceView(OrganisationView.newInstance(textView.getText().toString()), true);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_my_organisations_btn_add_orga:
                // Page Change
               ((MainActivity) getActivity()).replaceView(organisationCreationView, true);
                break;
            case R.id.top_bar_my_organisations_return:
                ((MainActivity) getActivity()).goToPreviousPage();
                break;
        }
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
    public void setConnectionInternetError(String error) {
        dialog.setTitle("Problème de connection");
        dialog.setMessage("Vérifiez votre connexion Internet");
        dialog.show();
    }

    @Override
    public void updateListView() {
        listView.invalidateViews();
    }
}
