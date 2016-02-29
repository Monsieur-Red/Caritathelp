package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Views.SubMenu.MyOrganisations.MyOrganisationsView;

/**
 * Created by pierr on 24/02/2016.
 */
public class MyOrganisationsPresenter implements IMyOrganisationsPresenter, IOnMyOrganisationsFinishedListener {

    private MyOrganisationsView         view;
    private MyOrganisationsInteractor   interactor;

    public MyOrganisationsPresenter(MyOrganisationsView view, User user, Network network) {
        this.view = view;
        interactor = new MyOrganisationsInteractor(view.getContext(), user, network);
    }

    @Override
    public void getMyOrganisations() {
        view.showProgress();
        interactor.getMyOrganisations(this);
    }

    @Override
    public void onConnectionInternetError(String error) {
        view.hideProgress();
        view.setConnectionInternetError(error);
    }

    @Override
    public void onSuccess() {
        view.hideProgress();
        view.updateListView();
    }
}
