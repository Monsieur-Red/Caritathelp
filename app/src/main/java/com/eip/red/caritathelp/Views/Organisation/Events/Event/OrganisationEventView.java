package com.eip.red.caritathelp.Views.Organisation.Events.Event;

import android.app.AlertDialog;
import android.app.Fragment;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Home.News;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Presenters.Organisation.Events.Event.OrganisationEventPresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;

import java.util.List;

/**
 * Created by pierr on 18/03/2016.
 */

public class OrganisationEventView extends Fragment implements IOrganisationEventView, View.OnClickListener {

    private OrganisationEventPresenter presenter;

    private RecyclerView    recyclerView;
    private ProgressBar     progressBar;
    private AlertDialog     dialog;


    public static OrganisationEventView newInstance(int eventId, String title) {
        OrganisationEventView    myFragment = new OrganisationEventView();

        Bundle args = new Bundle();
        args.putInt("event id", eventId);
        args.putString("event title", title);
        myFragment.setArguments(args);

        return (myFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Network Model & Id Organisation
        Network network = ((MainActivity) getActivity()).getModelManager().getNetwork();
        int     eventId = getArguments().getInt("event id");

        // Init Presenter
        presenter = new OrganisationEventPresenter(this, network, eventId);

        // Init Dialog
        dialog = new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .create();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_organisation_event, container, false);

        // Set ToolBar
        ((MainActivity) getActivity()).getToolBar().update("Événement", true);

        // Init SearchBar
        ((MainActivity) getActivity()).getToolBar().getSearchBar().setVisibility(View.GONE);

        // Init UI Element
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Get Event Title
        String title = getArguments().getString("event title");

        // Set Event Title
        TextView    titleTV = (TextView) view.findViewById(R.id.title);
        titleTV.setText(Tools.upperCaseFirstLetter(title));

        // Init Image Filter (Darken the image)
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        LightingColorFilter lcf = new LightingColorFilter(0xFF888888, 0x00222222);
        imageView.setColorFilter(lcf);

        // Init RecyclerView & Listener & Adapter
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new OrganisationEventRVAdapter());

        // Init LayoutManager
        LinearLayoutManager llayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(llayoutManager);

        // Init Listener
        view.findViewById(R.id.btn_join).setOnClickListener(this);
        view.findViewById(R.id.btn_members).setOnClickListener(this);
        view.findViewById(R.id.btn_informations).setOnClickListener(this);

        // Init Event

        // Init News Model
//        presenter.getNews();


        return (view);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDialogError(String title, String msg) {
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show();
    }

    @Override
    public void updateRecyclerView(List<News> news) {
        ((OrganisationEventRVAdapter) recyclerView.getAdapter()).update(news);
    }

    @Override
    public void onClick(View v) {
        presenter.onClick(v.getId());
    }
}
