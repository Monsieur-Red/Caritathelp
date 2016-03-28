package com.eip.red.caritathelp.Views.Organisation.Management;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Organisation.Management.OrganisationEventCreation.OrganisationEventCreationView;

/**
 * Created by pierr on 24/02/2016.
 */
public class OrganisationManagementView extends Fragment implements View.OnClickListener{

    private int organisationId;

    public static OrganisationManagementView newInstance(int idOrganisation) {
        OrganisationManagementView    myFragment = new OrganisationManagementView();

        Bundle args = new Bundle();
        args.putInt("organisation id", idOrganisation);
        myFragment.setArguments(args);

        return (myFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Network Model & Id Organisation
        Network network = ((MainActivity) getActivity()).getModelManager().getNetwork();
        int     organisationId = getArguments().getInt("organisation id");

        this.organisationId = organisationId;
        // Init Presenter
//        presenter = new OrganisationMembersPresenter(this, network, organisationId);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_organisation_management, container, false);

        // Set ToolBar
        ((MainActivity) getActivity()).getToolBar().update("Gestion", true);

        // Init SearchBar
        ((MainActivity) getActivity()).getToolBar().getSearchBar().setVisibility(View.GONE);

        // Init Listener
        view.findViewById(R.id.organisation_management_create_event).setOnClickListener(this);

        return (view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.organisation_management_create_event:
                ((MainActivity) getActivity()).replaceView(OrganisationEventCreationView.newInstance(organisationId), Animation.FADE_IN_OUT);
                break;
        }
    }

}
