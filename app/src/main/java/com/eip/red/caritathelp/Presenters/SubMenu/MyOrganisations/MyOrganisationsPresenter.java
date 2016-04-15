package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;
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

    public MyOrganisationsPresenter(MyOrganisationsView view, User user, Network network) {
        this.view = view;

        // Init Interactor
        interactor = new MyOrganisationsInteractor(view.getActivity().getBaseContext(), user, network);
    }

    @Override
    public void onClick(int viewId) {
        if (viewId == R.id.btn_create)
            Tools.replaceView(view, OrganisationCreationView.newInstance(), Animation.FADE_IN_OUT, false);
    }

    @Override
    public void getMyOrganisations() {
        view.showProgress();
        interactor.getMyOrganisations(view.getProgressBar(), this);
    }

    @Override
    public void goToOrganisationView(Organisation organisation) {
        if (organisation != null)
            Tools.replaceView(view, OrganisationView.newInstance(organisation), Animation.FADE_IN_OUT, false);
    }

    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
        view.setDialogError(title, msg);
    }

    @Override
    public void onSuccess(List<Organisation> myOrganisationsOwner, List<Organisation> myOrganisationsMember) {
        view.initRecyclerViews(view.getView(), myOrganisationsOwner, myOrganisationsMember);
//        view.updateRecyclerView(myOrganisationsOwner, myOrganisationsMember);
        view.hideProgress();
    }

}
