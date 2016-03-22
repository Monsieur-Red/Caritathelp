package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Organisation.OrganisationView;
import com.eip.red.caritathelp.Views.SubMenu.MyOrganisations.MyOrganisationsView;
import com.eip.red.caritathelp.Views.SubMenu.MyOrganisations.OrganisationCreation.OrganisationCreationView;

import java.util.List;

/**
 * Created by pierr on 24/02/2016.
 */
public class MyOrganisationsPresenter implements IMyOrganisationsPresenter, IOnMyOrganisationsFinishedListener {

    private MyOrganisationsView         view;
    private MyOrganisationsInteractor   interactor;

    private OrganisationCreationView    organisationCreationView;
    private OrganisationView            organisationView;

    public MyOrganisationsPresenter(MyOrganisationsView view, User user, Network network) {
        this.view = view;

        // Init Interactor
        interactor = new MyOrganisationsInteractor(view.getActivity().getBaseContext(), user, network);

        // Init View
        organisationCreationView = new OrganisationCreationView();
        organisationView = new OrganisationView();
    }

    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.top_bar_my_organisations_btn_add_orga:
                // Page Change
                ((MainActivity) view.getActivity()).replaceView(organisationCreationView, Animation.SLIDE_UP_DOWN);
                break;
            case R.id.top_bar_my_organisations_return:
                ((MainActivity) view.getActivity()).goToPreviousPage();
                break;
        }
    }

    @Override
    public void getMyOrganisations() {
        view.showProgress();
        interactor.getMyOrganisations(this);
    }

    @Override
    public void goToOrganisationView(Organisation organisation) {
        if (organisation != null)
            ((MainActivity) view.getActivity()).replaceView(OrganisationView.newInstance(organisation), Animation.FADE_IN_OUT);
    }

    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
        view.setDialogError(title, msg);
    }

    @Override
    public void onSuccess(List<Organisation> myOrganisations) {
        view.hideProgress();
        view.updateListView(myOrganisations);
    }
}
