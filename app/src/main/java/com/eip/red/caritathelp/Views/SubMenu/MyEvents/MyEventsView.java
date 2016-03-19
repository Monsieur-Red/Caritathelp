package com.eip.red.caritathelp.Views.SubMenu.MyEvents;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.TextKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.Presenters.SubMenu.MyEvents.MyEventsPresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;

import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 18/03/2016.
 */

public class MyEventsView extends Fragment implements IMyEventsView {

    private MyEventsPresenter presenter;

    private RecyclerView    recyclerView;
    private EditText        searchBar;
    private Button          cancelBtn;
    private ProgressBar     progressBar;
    private AlertDialog     dialog;


    public static MyEventsView newInstance(int userId) {
        MyEventsView    myFragment = new MyEventsView();

        Bundle args = new Bundle();
        args.putInt("user id", userId);
        myFragment.setArguments(args);

        return (myFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Network Model & Id Organisation
        Network network = ((MainActivity) getActivity()).getModelManager().getNetwork();
        int     userId = getArguments().getInt("user id");

        // Init Presenter
        presenter = new MyEventsPresenter(this, network, userId);

        // Init Dialog
        dialog = new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .create();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_submenu_my_events, container, false);

        // Set ToolBar
        ((MainActivity) getActivity()).getToolBar().update("Mes événements", true, false);

        // Init UI Element
        searchBar = (EditText) view.findViewById(R.id.search_text);
        cancelBtn = (Button) view.findViewById(R.id.btn_cancel);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Init RecyclerView & Listener & Adapter
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new MyEventsRVAdapter(presenter));

        // Init LayoutManager
        LinearLayoutManager llayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(llayoutManager);

        // Init Filter
        initSearchBarListener();

        // Init Listener
        initTopBarListener(view);

        // Init Events Model
        presenter.getMyEvents();

        return (view);
    }

    private void initSearchBarListener() {
        searchBar.addTextChangedListener(new TextWatcher() {

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
                if (TextUtils.isEmpty(arg0))
                    ((MyEventsRVAdapter) recyclerView.getAdapter()).flushFilter();
                else {
                    String text = searchBar.getText().toString().toLowerCase(Locale.getDefault());
                    ((MyEventsRVAdapter) recyclerView.getAdapter()).filter(text);
                }
            }
        });
    }

    private void initTopBarListener(final View view) {
        searchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Clear Text
                    TextKeyListener.clear(searchBar.getText());

                    // Hide Add Btn
//                    view.findViewById(R.id.btn_add).setVisibility(View.GONE);

                    // Show Cancel Btn
                    cancelBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Add Btn
//                view.findViewById(R.id.btn_add).setVisibility(View.VISIBLE);

                // Hide Cancel Btn
                cancelBtn.setVisibility(View.GONE);

                // Hide Keyboard
                Tools.hideKeyboard(getActivity().getApplicationContext(), getActivity().getCurrentFocus());

                // Search Bar EditText Clear Focus
                searchBar.clearFocus();
            }
        });


    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_cancel:
//                // Show Add Btn
//                view.findViewById(R.id.btn_add).setVisibility(View.VISIBLE);
//
//                // Hide Cancel Btn
//                cancelBtn.setVisibility(View.GONE);
//
//                // Hide Keyboard
//                Tools.hideKeyboard(getActivity().getApplicationContext(), getActivity().getCurrentFocus());
//
//                // Search Bar EditText Clear Focus
//                searchBar.clearFocus();
//                break;
//        }
//    }

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
        ((MyEventsRVAdapter) recyclerView.getAdapter()).update(events);
    }
}
