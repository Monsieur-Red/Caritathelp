package com.eip.red.caritathelp.Views.SubMenu.MyEvents;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
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
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Activities.Main.MySearchBar;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.MyWidgets.DividerItemDecoration;
import com.eip.red.caritathelp.MyWidgets.GridSpacingItemDecoration;
import com.eip.red.caritathelp.MyWidgets.MyEditText;
import com.eip.red.caritathelp.Presenters.SubMenu.MyEvents.MyEventsPresenter;
import com.eip.red.caritathelp.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 18/03/2016.
 */

public class MyEventsView extends Fragment implements IMyEventsView, View.OnClickListener {

    private MyEventsPresenter   presenter;

    private RecyclerView        recyclerView;
    private MyEventsRVAdapter   adapter;
    private View                dividerV;
    private Button              createBtn;
    private ProgressBar         progressBar;
    private AlertDialog         dialog;

    public static MyEventsView newInstance(int userId) {
        MyEventsView    myFragment = new MyEventsView();

        Bundle args = new Bundle();
        args.putInt("page", R.string.submenu_my_events);
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

        // Init UI Element
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        dividerV = (View) view.findViewById(R.id.divider_vertical);
        createBtn = (Button) view.findViewById(R.id.btn_create);

        // Init Spinner
        initSpinner(view);

        // Init RecyclerView
        initRecyclerView(view);

        // Init Listener
        view.findViewById(R.id.btn_create).setOnClickListener(this);

        return (view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init ToolBar Title
        getActivity().setTitle(getArguments().getInt("page"));

        // Init Events Model
        presenter.getMyEvents();

        // Init Organisations Model
        presenter.getMyOrganisations();
    }

//    private void initSearchBar() {
//        MySearchBar searchBar = ((MainActivity) getActivity()).getToolBar().getSearchBar();
//        final EditText    searchText = searchBar.getSearchText();
//        final ImageButton cancelBtn = searchBar.getCancelBtn();
//
//        // Show SearchBar
//        searchBar.setVisibility(View.VISIBLE);
//
//        // Show the SearchBar
//        searchBar.show(R.string.search_bar_event);
//
//        //Init SearchText listener & filter
//        searchText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                if (TextUtils.isEmpty(arg0)) {
//                    // Hide Cancel Btn
//                    cancelBtn.setVisibility(View.GONE);
//
//                    // Flush Filter
//                    ((MyEventsRVAdapter) recyclerView.getAdapter()).flushFilter();
//                }
//                else {
//                    // Show Cancel Btn
//                    cancelBtn.setVisibility(View.VISIBLE);
//
//                    // Filter text
//                    String text = searchText.getText().toString().toLowerCase(Locale.getDefault());
//                    ((MyEventsRVAdapter) recyclerView.getAdapter()).filter(text);
//                }
//            }
//        });
//    }

    private void initSpinner(View view) {
/*
        Spinner spinner = new Spinner(getContext());//(Spinner) view.findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.my_events_spinner, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
*/

        // Init Listener
/*        public class SpinnerActivity extends Activity implements OnItemSelectedListener {
            ...

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        }*/
    }

    private void initRecyclerView(View view) {
        // Init Recycler Views
        recyclerView = (RecyclerView) view.findViewById(R.id.my_events_recycler_view);

        // Init Adapter
        adapter = new MyEventsRVAdapter(presenter);

        // Set Adapter
        recyclerView.setAdapter(adapter);

        // Init LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Set Options to enable toolbar display/hide
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);

        // Init Divider (between items)
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);

        recyclerView.addItemDecoration(itemDecoration);
        // Convert dp to px
//        int spacing = Math.round(10 * getResources().getDisplayMetrics().density);
//        RecyclerView.ItemDecoration itemDecoration = new GridSpacingItemDecoration(3, spacing, true);
//        recyclerViewOwner.addItemDecoration(itemDecoration);
//        recyclerViewMember.addItemDecoration(itemDecoration);
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
    public void updateRecyclerView(List<Event> myEventsOwner, List<Event> myEventsMember) {
        adapter.update(myEventsOwner);
    }

    public void setVisibilityCreationPart(int visibility) {
        dividerV.setVisibility(visibility);
        createBtn.setVisibility(visibility);
    }
}
