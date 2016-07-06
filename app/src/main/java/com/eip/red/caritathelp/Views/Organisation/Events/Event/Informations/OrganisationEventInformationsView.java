package com.eip.red.caritathelp.Views.Organisation.Events.Event.Informations;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User.User;
import com.eip.red.caritathelp.Presenters.Organisation.Events.Event.Informations.OrganisationEventInformationsPresenter;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 18/03/2016.
 */

public class OrganisationEventInformationsView extends Fragment implements IOrganisationEventInformationsView {

    private OrganisationEventInformationsPresenter presenter;

    private TextView    dateBegin;
    private TextView    dateEnd;
    private TextView    location;
    private TextView    description;
    private ProgressBar progressBar;
    private AlertDialog dialog;


    public static OrganisationEventInformationsView newInstance(int eventId) {
        OrganisationEventInformationsView    myFragment = new OrganisationEventInformationsView();

        Bundle args = new Bundle();
        args.putInt("page", R.string.view_name_organisation_informations);
        args.putInt("event id", eventId);
        myFragment.setArguments(args);

        return (myFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get User Model & Id Organisation
        User    user = ((MainActivity) getActivity()).getModelManager().getUser();
        int     eventId = getArguments().getInt("event id");

        // Init Presenter
        presenter = new OrganisationEventInformationsPresenter(this, user.getToken(), eventId);

        // Init Dialog
        dialog = new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .create();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_organisation_event_informations, container, false);

        // Init UI Element
        dateBegin = (TextView) view.findViewById(R.id.date_begin);
        dateEnd = (TextView) view.findViewById(R.id.date_end);
        location = (TextView) view.findViewById(R.id.location);
        description = (TextView) view.findViewById(R.id.description);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        return (view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init ToolBar Title
        getActivity().setTitle(getArguments().getInt("page"));

        // Init Event Model
        presenter.getEvent();
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
    public void setViewData(String dateBegin, String dateEnd, String location, String description) {
        this.dateBegin.setText(dateBegin);
        this.dateEnd.setText(dateEnd);
        this.location.setText(location);
        this.description.setText(description);
    }

}
