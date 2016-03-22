package com.eip.red.caritathelp.Views.Home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Home.NewsList;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 16/11/2015.
 */

public class HomeView extends Fragment {

    private NewsList    newsList;

    private View        view;
    private ListView    listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get News List
        newsList = ((MainActivity) getActivity()).getModelManager().getNewsList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        // Set ToolBar
        ((MainActivity) getActivity()).getToolBar().update("Actualités", false, false);

        // Init ListView & Listener & Adapter
        listView = (ListView)view.findViewById(R.id.home_list_view);
        listView.setAdapter(new HomeListViewAdapter(this, newsList));
        initListener();

        return (view);
    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

}

