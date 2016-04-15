package com.eip.red.caritathelp.Views.Home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Home.NewsList;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 16/11/2015.
 */

public class HomeView extends Fragment implements IHomeView {

    private NewsList        newsList;

    private RecyclerView    recyclerView;
    private ProgressBar     progressBar;
    private AlertDialog     dialog;

    public static Fragment newInstance() {
        HomeView     fragment = new HomeView();
        Bundle          args = new Bundle();

        args.putInt("page", R.string.view_name_home);
        fragment.setArguments(args);

        return (fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get News List
        newsList = ((MainActivity) getActivity()).getModelManager().getNewsList();

        // Init Dialog
        dialog = new AlertDialog.Builder(getContext())
                .setCancelable(true)
                .create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_home, container, false);

        // Init UI Element
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Init RecyclerView
        initRecyclerView(view);

        return (view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init ToolBar Title
        getActivity().setTitle(getArguments().getInt("page"));

        // Init NewsList Model
//        presenter.getNewsList();
    }

    private void initRecyclerView(View view) {
        // Init RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new HomeRVAdapter(newsList));

        // Init LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
    public void updateRecyclerView() {
//        ((HomeRVAdapter) recyclerView.getAdapter()).update(events);
    }
}

