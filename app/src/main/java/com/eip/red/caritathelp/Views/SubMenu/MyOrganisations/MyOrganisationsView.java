package com.eip.red.caritathelp.Views.SubMenu.MyOrganisations;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations.MyOrganisationsPresenter;
import com.eip.red.caritathelp.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 16/11/2015.
 */
public class MyOrganisationsView extends Fragment implements IMyOrganisationsView, View.OnClickListener{

    private MyOrganisationsPresenter    presenter;

    private ListView        listView;
    private EditText        searchBar;
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

        // Init Dialog
        dialog = new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .create();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_submenu_my_organisations, container, false);

        // Set ToolBar
        ((MainActivity) getActivity()).getToolBar().update("Mes associations", true, false);

        // Init UI Element
        searchBar = (EditText) view.findViewById(R.id.top_bar_my_organisations_search_text);
        progressBar = (ProgressBar)  view.findViewById(R.id.my_organisations_progress_bar);

        // Init ListView & Listener & Adapter
        listView = (ListView)view.findViewById(R.id.orga_list_view);
        listView.setAdapter(new MyOrganisationsListViewAdapter(this));
        initListViewListener();

        // Init Filter
        initSearchBarListener();

        // Init Button Listener
        view.findViewById(R.id.top_bar_my_organisations_return).setOnClickListener(this);
        view.findViewById(R.id.top_bar_my_organisations_btn_add_orga).setOnClickListener(this);

        // Init MyOrganisation Model by requesting the api
        presenter.getMyOrganisations();

        return (view);
    }

    private void initListViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Init Text Search Bar
                searchBar.invalidate();
                searchBar.getText().clear();
                searchBar.setHint(android.R.string.search_go);

                // Go to organisation page
                presenter.goToOrganisationView((Organisation) listView.getItemAtPosition(position));
            }
        });
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
                String  text = searchBar.getText().toString().toLowerCase(Locale.getDefault());

                ((MyOrganisationsListViewAdapter) listView.getAdapter()).filter(text);
            }
        });
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
    public void updateListView(List<Organisation> myOrganisations) {
        ((MyOrganisationsListViewAdapter) listView.getAdapter()).setMyOrganisations(myOrganisations);
        ((MyOrganisationsListViewAdapter) listView.getAdapter()).notifyDataSetChanged();
    }

}
