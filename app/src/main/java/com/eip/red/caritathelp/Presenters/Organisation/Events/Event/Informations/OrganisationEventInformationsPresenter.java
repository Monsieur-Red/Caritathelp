package com.eip.red.caritathelp.Presenters.Organisation.Events.Event.Informations;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.EventInformations;
import com.eip.red.caritathelp.Tools;
import com.eip.red.caritathelp.Views.Organisation.Events.Event.Informations.OrganisationEventInformationsView;

/**
 * Created by pierr on 18/03/2016.
 */

public class OrganisationEventInformationsPresenter implements IOrganisationEventInformationsPresenter, IOnOrganisationEventInformationsFinishedListener {

    private OrganisationEventInformationsView           view;
    private OrganisationEventInformationsInteractor     interactor;

    public OrganisationEventInformationsPresenter(OrganisationEventInformationsView view, Network network, int eventId) {
        this.view = view;

        interactor = new OrganisationEventInformationsInteractor(view.getActivity().getApplicationContext(), network, eventId);
    }

    @Override
    public void getEventInformations() {
        view.showProgress();
        interactor.getEventInformations(this);
    }

    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
        view.setDialogError(title, msg);
    }

    @Override
    public void onSuccess(EventInformations event) {
        view.hideProgress();
        view.setViewData(event.getBegin(), event.getEnd(), Tools.upperCaseFirstLetter(event.getPlace()), event.getDescription());
    }
}
