package com.eip.red.caritathelp.Views.MyEvents;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 16/11/2015.
 */
public class MyEventsView extends Fragment {

    public static final int PAGE_HOME = 0;
    public static final int PAGE_EVENTS = 1;
    public static final int PAGE_ORGANISATIONS = 2;

    private int         page;
    private View        view;
    private ListView    listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events, container, false);

        // INIT Spinner
        initSpinner();


        // Init ListView & Listener & Adapter
        listView = (ListView)view.findViewById(R.id.events_list_view);
        listView.setAdapter(new EventsListViewAdapter(this));
        initListener();

        return (view);
    }

    private void initSpinner() {
        initDays();
        initMonths();
        initYears();
    }

    private void initDays() {
        Spinner                 dSpinner = (Spinner)view.findViewById(R.id.events_filter_day);
        String[]                days = {"Tous", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String>    dAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, days);

        dAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dSpinner.setAdapter(dAdapter);
        dSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initMonths() {
        Spinner                 mSpinner = (Spinner)view.findViewById(R.id.events_filter_month);
        String[]                months = {"Tous", "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
        ArrayAdapter<String>    mAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, months);

        mAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initYears() {
        Spinner                 ySpinner = (Spinner)view.findViewById(R.id.events_filter_year);
        String[]                years = {"Tous", "2015", "2016", "2017", "2018", "2019", "2020"};
        ArrayAdapter<String>    yAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, years);

        yAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ySpinner.setAdapter(yAdapter);
        ySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

}

