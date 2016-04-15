package com.eip.red.caritathelp.Presenters.Organisation.Events.Event.Informations;

import android.app.AlertDialog;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.Models.Organisation.EventInformations;
import com.eip.red.caritathelp.Tools;
import com.eip.red.caritathelp.Views.Organisation.Events.Event.Informations.OrganisationEventInformationsView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by pierr on 18/03/2016.
 */

public class OrganisationEventInformationsPresenter implements IOrganisationEventInformationsPresenter, IOnOrganisationEventInformationsFinishedListener {

    private OrganisationEventInformationsView           view;
    private OrganisationEventInformationsInteractor     interactor;

    private DateTimeFormatter formatter;
    private DateTimeFormatter   newFormatter;

    public OrganisationEventInformationsPresenter(OrganisationEventInformationsView view, Network network, int eventId) {
        this.view = view;

        // Init Interactor
        interactor = new OrganisationEventInformationsInteractor(view.getActivity().getApplicationContext(), network, eventId);

        // Init DateTimeFormatter
        formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
        newFormatter = DateTimeFormat.forPattern("'Le' E dd MMMM Y 'à' HH:mm");
    }

    @Override
    public void getEvent() {
        view.showProgress();
        interactor.getEventInformations(this);
    }

    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
        view.setDialogError(title, msg);
    }

    @Override
    public void onSuccess(Event event) {
        // Set DateTime
        DateTime    beginDate = formatter.parseDateTime(event.getBegin());
        DateTime    endDate = formatter.parseDateTime(event.getEnd());

        // Set View Data
        view.setViewData(newFormatter.print(beginDate), newFormatter.print(endDate), event.getPlace(), event.getDescription());

        // Set Progress Bar Visibility
        view.hideProgress();
    }

}
