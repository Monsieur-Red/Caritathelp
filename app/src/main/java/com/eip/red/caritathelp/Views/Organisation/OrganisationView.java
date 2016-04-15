package com.eip.red.caritathelp.Views.Organisation;

import android.app.AlertDialog;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Home.News;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.MyWidgets.DividerItemDecoration;
import com.eip.red.caritathelp.Presenters.Organisation.OrganisationPresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;
import com.eip.red.caritathelp.Views.Organisation.Events.OrganisationEventsRVAdapter;
import com.pkmmte.view.CircularImageView;

import java.util.List;

/**
 * Created by pierr on 18/02/2016.
 */

public class OrganisationView extends Fragment implements IOrganisationView, View.OnClickListener {

    private OrganisationPresenter   presenter;

    private View            view;
    private RecyclerView    recyclerView;
    private ProgressBar     progressBar;
    private AlertDialog     dialog;

    public static OrganisationView newInstance(Organisation organisation) {
        OrganisationView    myFragment = new OrganisationView();

        Bundle args = new Bundle();

        args.putString("page", organisation.getName());
        args.putSerializable("organisation", organisation);
        myFragment.setArguments(args);

        return (myFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Network & Organisation Model
//        Network         network = ((SecondContainer) getActivity()).getModelManager().getNetwork();
        Network         network = ((MainActivity) getActivity()).getModelManager().getNetwork();
        Organisation    organisation = (Organisation) getArguments().getSerializable("organisation");

        // Init Presenter
        presenter = new OrganisationPresenter(this, network, organisation);

        // Init Dialog
        dialog = new AlertDialog.Builder(getContext())
                .setCancelable(true)
                .create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_organisation, container, false);

        // Init UI Element
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Init RecyclerView
        initRecyclerView();

        // Init Listener
        view.findViewById(R.id.btn_join).setOnClickListener(this);
        view.findViewById(R.id.btn_follow).setOnClickListener(this);
        view.findViewById(R.id.btn_post).setOnClickListener(this);
        view.findViewById(R.id.btn_members).setOnClickListener(this);
        view.findViewById(R.id.btn_management).setOnClickListener(this);
        view.findViewById(R.id.btn_events).setOnClickListener(this);
        view.findViewById(R.id.btn_informations).setOnClickListener(this);

        return (view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init ToolBar Title
        getActivity().setTitle(Tools.upperCaseFirstLetter(getArguments().getString("page")));

        // Get Organisation Model
        presenter.getOrganisation();
    }

    private void initRecyclerView() {
        // Init RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new OrganisationRVAdapter(presenter));

        // Init LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set Options to enable toolbar display/hide
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);

        // Init Divider (between items)
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this.getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }


    @Override
    public void onClick(View v) {
        presenter.onClick(v.getId());
    }


    @Override
    public void initView(String right) {
        if (!right.equals("owner")) {
            // Set Logo Position
            CircularImageView logo = (CircularImageView) view.findViewById(R.id.logo);
            logo.bringToFront();
            logo.invalidate();

            // Set Management Btn Visibility
            view.findViewById(R.id.btn_management).setVisibility(View.INVISIBLE);
        }
        else {
            // Set Logo Position
            CircularImageView logo = (CircularImageView) view.findViewById(R.id.logo);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) logo.getLayoutParams();

            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
            logo.setLayoutParams(layoutParams);

            // Set Management Btn Visibility
            view.findViewById(R.id.btn_management).setVisibility(View.VISIBLE);
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
    public void setDialog(String title, String msg) {
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show();
    }

    @Override
    public void updateRV(List<News> newsList) {

    }
}
