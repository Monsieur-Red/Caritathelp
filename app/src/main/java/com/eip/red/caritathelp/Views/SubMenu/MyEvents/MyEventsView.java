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
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Activities.Main.MySearchBar;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.MyWidgets.DividerItemDecoration;
import com.eip.red.caritathelp.MyWidgets.MyEditText;
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
    private ProgressBar     progressBar;
    private AlertDialog     dialog;

    private boolean searchBFocus = false;


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
        ((MainActivity) getActivity()).getToolBar().update("Mes événements", true);

        // Init SearchBar
        initSearchBar();

        // Init UI Element
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Init RecyclerView
        initRecyclerView(view);

        // Init Events Model
        presenter.getMyEvents();

        return (view);
    }

    private void initSearchBar() {
        MySearchBar searchBar = ((MainActivity) getActivity()).getToolBar().getSearchBar();
        final EditText    searchText = searchBar.getSearchText();
        final ImageButton cancelBtn = searchBar.getCancelBtn();

        // Show SearchBar
        searchBar.setVisibility(View.VISIBLE);

        // Show the SearchBar
        searchBar.show(R.string.search_bar_event);

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
                    ((MyEventsRVAdapter) recyclerView.getAdapter()).flushFilter();
                }
                else {
                    // Show Cancel Btn
                    cancelBtn.setVisibility(View.VISIBLE);

                    // Filter text
                    String text = searchText.getText().toString().toLowerCase(Locale.getDefault());
                    ((MyEventsRVAdapter) recyclerView.getAdapter()).filter(text);
                }
            }
        });
    }

    private void initRecyclerView(View view) {
        // Init RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new MyEventsRVAdapter(presenter));

        // Init LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        // Init Divider (between items)
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this.getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
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
    public void updateRecyclerView(List<Event> events) {
        ((MyEventsRVAdapter) recyclerView.getAdapter()).update(events);
    }
}
