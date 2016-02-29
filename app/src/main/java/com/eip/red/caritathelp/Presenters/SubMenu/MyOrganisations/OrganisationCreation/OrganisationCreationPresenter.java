package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations.OrganisationCreation;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Views.SubMenu.MyOrganisations.OrganisationCreation.OrganisationCreationView;

/**
 * Created by pierr on 24/02/2016.
 */

public class OrganisationCreationPresenter implements IOrganisationCreationPresenter, IOnOrganisationCreationsFinishedListener {

    private OrganisationCreationView        view;
    private OrganisationCreationInteractor  interactor;

    public OrganisationCreationPresenter(OrganisationCreationView view, Network network) {
        this.view = view;
        interactor = new OrganisationCreationInteractor(view.getContext(), network);
    }

    @Override
    public void create(String name, String theme, String city, String description) {
        view.showProgress();
        interactor.create(name, theme, city, description, this);
    }

    @Override
    public void onNameError(String error) {
        view.hideProgress();
        view.setNameError(error);
    }

    @Override
    public void onThemeError(String error) {
        view.hideProgress();
        view.setThemeError(error);
    }

    @Override
    public void onCityError(String error) {
        view.hideProgress();
        view.setCityError(error);
    }

    @Override
    public void onDescriptionError(String error) {
        view.hideProgress();
        view.setDescriptionError(error);
    }

    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
        view.setDialogError(title, msg);
    }

    @Override
    public void onSuccess(String name) {
        view.hideProgress();
        view.navigateToOrganisationView(name);
    }
}
