package com.eip.red.caritathelp.Presenters.Organisation;

import com.eip.red.caritathelp.MainActivity.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Organisation.OrganisationManagement.OrganisationManagementView;
import com.eip.red.caritathelp.Views.Organisation.OrganisationMembers.OrganisationMembersView;
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
        interactor = new OrganisationInteractor(view.getContext(), network, organisation);

        // Init Views
        organisationManagementView = new OrganisationManagementView();
        organisationMembersView = new OrganisationMembersView();
    }

    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.top_bar_organisation_return:
                ((MainActivity) view.getActivity()).goToPreviousPage();
                break;
            case R.id.top_bar_organisation_management:
                ((MainActivity) view.getActivity()).replaceView(organisationManagementView, true);
                break;
            case R.id.organisation_btn_members:
                ((MainActivity) view.getActivity()).replaceView(OrganisationMembersView.newInstance(interactor.getOrganisationId()), true);
                break;
        }

    }

    @Override
    public String getOrganisationName() {
        return (interactor.getOrganisationName());
    }
}
