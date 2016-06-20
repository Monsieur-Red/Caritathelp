package com.eip.red.caritathelp.Presenters.Organisation.Informations;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Views.Organisation.Informations.OrganisationInformationsView;

import java.util.List;

/**
 * Created by pierr on 11/05/2016.
 */

public class OrganisationInformationsPresenter implements IOrganisationInformationsPresenter, IOnOrganisationInformationsFinishedListener {

    OrganisationInformationsView        view;
    OrganisationInformationsInteractor  interactor;

    public OrganisationInformationsPresenter(OrganisationInformationsView view, String token, int id) {
        this.view = view;
        interactor = new OrganisationInformationsInteractor(view.getContext(), token, id);
    }

    @Override
    public void getOrganisationInformations() {
        view.showProgress();
        interactor.getOrganisationInformations(this, view.getProgressBar());
    }


    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
        view.setDialog(title, msg);
    }

    @Override
    public void onSuccess(Organisation organisation) {
        // Set View Data
        view.getName().setText(organisation.getName());
        /****** IL MANQUE LE NOMBRE DE MEMBRES DS LA REQUETE !! *****/
        view.getLocation_members().setText(organisation.getCity());
        view.getDescription().setText(organisation.getDescription());

        // Set ProgressBar Visibility
        view.hideProgress();
    }
}
