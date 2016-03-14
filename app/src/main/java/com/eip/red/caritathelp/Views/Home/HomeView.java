package com.eip.red.caritathelp.Views.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.eip.red.caritathelp.Models.Home.NewsList;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Main.MainActivity;

/**
 * Created by pierr on 16/11/2015.
 */
public class HomeView extends Fragment {

    public static final int PAGE_HOME = 0;
    public static final int PAGE_EVENTS = 1;
    public static final int PAGE_ORGANISATIONS = 2;

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

