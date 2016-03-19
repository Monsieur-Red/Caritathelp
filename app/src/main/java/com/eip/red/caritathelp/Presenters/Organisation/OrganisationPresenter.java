package com.eip.red.caritathelp.Presenters.Organisation;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Organisation.Events.OrganisationEventsView;
import com.eip.red.caritathelp.Views.Organisation.Management.OrganisationManagementView;
import com.eip.red.caritathelp.Views.Organisation.Members.OrganisationMembersView;
import com.eip.red.caritathelp.Views.Organisation.OrganisationView;

/**
 * Created by pierr on 11/03/2016.
 */

public class OrganisationPresenter implements IOrganisationPresenter {

    private OrganisationView        view;
    private OrganisationInteractor  interactor;

    private OrganisationManagementView  organisationManagementView;
    private OrganisationMembersView     organisationMembersView;


    public OrganisationPresenter(OrganisationView view, Network network, Organisation organisation) {
        this.view = view;

        // Init Interactor
        interactor = new OrganisationInteractor(view.getActivity().getBaseContext(), network, organisation);

        // Init Views
        organisationManagementView = new OrganisationManagementView();
        organisationMembersView = new OrganisationMembersView();
    }

    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.organisation_btn_management:
                ((MainActivity) view.getActivity()).replaceView(OrganisationManagementView.newInstance(interactor.getOrganisationId()), Animation.FLIP_LEFT_RIGHT);
                break;
            case R.id.organisation_btn_join:
                break;
            case R.id.organisation_btn_members:
                ((MainActivity) view.getActivity()).replaceView(OrganisationMembersView.newInstance(interactor.getOrganisationId()), Animation.FLIP_LEFT_RIGHT);
                break;
            case R.id.organisation_btn_events:
                ((MainActivity) view.getActivity()).replaceView(OrganisationEventsView.newInstance(interactor.getOrganisationId()), Animation.FLIP_LEFT_RIGHT);
                break;
        }

    }

    @Override
    public String getOrganisationName() {
        return (interactor.getOrganisationName());
    }
}
