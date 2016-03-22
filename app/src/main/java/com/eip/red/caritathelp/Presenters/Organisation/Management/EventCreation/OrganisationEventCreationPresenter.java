package com.eip.red.caritathelp.Presenters.Organisation.Management.EventCreation;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Organisation.Events.Event.OrganisationEventView;
import com.eip.red.caritathelp.Views.Organisation.Management.OrganisationEventCreation.OrganisationEventCreationView;

/**
 * Created by pierr on 18/03/2016.
 */

public class OrganisationEventCreationPresenter implements IOrganisationEventCreationPresenter, IOnOrganisationEventCreationFinishedListener {

    private OrganisationEventCreationView       view;
    private OrganisationEventCreationInteractor interactor;

    public OrganisationEventCreationPresenter(OrganisationEventCreationView view, Network network, int organisationId) {
        this.view = view;

        interactor = new OrganisationEventCreationInteractor(view.getActivity().getApplicationContext(), network, organisationId);
    }


    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.date_begin:
                view.getDateBeginDialog().show();
                break;
            case R.id.date_end:
                view.getDateEndDialog().show();
                break;
            case R.id.btn_create:
                view.showProgress();
                interactor.createEvent(this, view.getData());
                break;
        }
    }

    @Override
    public void onTitleError(String error) {
        view.hideProgress();
        view.setTitleError(error);
    }

    @Override
    public void onPhotoError(String error) {
        view.hideProgress();
        view.setPhotoError(error);
    }

    @Override
    public void onBeginDateError(String error) {
        view.hideProgress();
        view.setBeginDateError(error);
    }

    @Override
    public void onEndDateError(String error) {
        view.hideProgress();
        view.setEndDateError(error);
    }

    @Override
    public void onLocationError(String error) {
        view.hideProgress();
        view.setLocationError(error);
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
    public void onSuccess(int eventId, String eventTitle) {
        /* NO ERROR, I JUST NEEDED A DIALOG TO DISPLAY THE EVENT CREATION SUCCESS */
        view.setDialogError("Création réussie", "Bienvenue sur la page d'accueil de l'événement " + eventTitle);

        /* Go To Event Page */
        ((MainActivity) view.getActivity()).replaceView(OrganisationEventView.newInstance(eventId, eventTitle), Animation.FADE_IN_OUT);
    }
}
