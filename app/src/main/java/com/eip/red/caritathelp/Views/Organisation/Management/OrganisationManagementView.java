package com.eip.red.caritathelp.Views.Organisation.Management;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User.User;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;
import com.eip.red.caritathelp.Views.Organisation.Events.Event.OrganisationEventView;
import com.eip.red.caritathelp.Views.Organisation.Management.OrganisationEventCreation.OrganisationEventCreationView;

/**
 * Created by pierr on 24/02/2016.
 */
public class OrganisationManagementView extends Fragment implements View.OnClickListener{

    private int organisationId;

    public static OrganisationManagementView newInstance(int idOrganisation) {
        OrganisationManagementView    myFragment = new OrganisationManagementView();

        Bundle args = new Bundle();
        args.putInt("page", R.string.view_name_organisation_management);
        args.putInt("organisation id", idOrganisation);
        myFragment.setArguments(args);

        return (myFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get User Model & Id Organisation
        User    user = ((MainActivity) getActivity()).getModelManager().getUser();
        int     organisationId = getArguments().getInt("organisation id");

        this.organisationId = organisationId;
        // Init Presenter
//        presenter = new OrganisationMembersPresenter(this, network, organisationId);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_organisation_management, container, false);

        // Init Listener
        view.findViewById(R.id.organisation_management_create_event).setOnClickListener(this);

        return (view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init ToolBar Title
        getActivity().setTitle(getArguments().getInt("page"));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.organisation_management_create_event:
                Tools.replaceView(this, OrganisationEventCreationView.newInstance(organisationId), Animation.FADE_IN_OUT, false);
                break;
        }
    }

}
