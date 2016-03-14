package com.eip.red.caritathelp.Presenters.Organisation.OrganisationMembers;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Member;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Organisation.OrganisationMembers.OrganisationMembersView;

import java.util.List;

/**
 * Created by pierr on 11/03/2016.
 */

public class OrganisationMembersPresenter implements IOrganisationMembersPresenter, IOnOrganisationMembersFinishedListener {

    private OrganisationMembersView         view;
    private OrganisationMembersInteractor   interactor;

    public OrganisationMembersPresenter(OrganisationMembersView view, Network network, int organisationId) {
        this.view = view;
        interactor = new OrganisationMembersInteractor(view.getContext(), network, organisationId);
    }


    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.top_bar_organisation_members_return:
                ((MainActivity) view.getActivity()).goToPreviousPage();
                break;
        }

    }

    @Override
    public void getMembers() {
        view.showProgress();
        interactor.getMembers(this);
    }

    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
    }

    @Override
    public void onSuccess(List<Member> members) {
        view.updateListView(members);
        view.hideProgress();
    }
}
