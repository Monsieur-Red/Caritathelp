package com.eip.red.caritathelp.Views.SubMenu.Friends;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Presenters.SubMenu.Friends.FriendsPresenter;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 19/04/2016.
 */

public class FriendsView extends Fragment implements View.OnClickListener {

    private FriendsPresenter    presenter;

    private RecyclerView    myFriendsRV;
    private RecyclerView    invitationsRV;
    private RecyclerView    sentRV;
    private ProgressBar     progressBar;
    private AlertDialog     dialog;

    public static FriendsView newInstance() {
        FriendsView    myFragment = new FriendsView();

        Bundle args = new Bundle();
        args.putInt("page", R.string.view_name_submenu_friends);
        myFragment.setArguments(args);

        return (myFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Network Model
//        Network network = ((MainActivity) getActivity()).getModelManager().getNetwork();

        // Init Presenter
        presenter = new FriendsPresenter(this);

        // Init Dialog
        dialog = new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submenu_friends, container, false);

        // Init UI Element
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Init Listener
        view.findViewById(R.id.btn_add).setOnClickListener(this);

        return (view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init ToolBar Title
        getActivity().setTitle(getArguments().getInt("page"));

        // Init Events Model
//        presenter.getMyEvents();
    }


    @Override
    public void onClick(View v) {
        presenter.onClick(v.getId());
    }
}
