package com.eip.red.caritathelp.Presenters.Organisation.Events;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.Views.Organisation.Events.Event.OrganisationEventView;
import com.eip.red.caritathelp.Views.Organisation.Events.OrganisationEventsView;

import java.util.List;

/**
 * Created by pierr on 18/03/2016.
 */
public class OrganisationEventsPresenter implements IOrganisationEventsPresenter, IOnOrganisationEventsFinishedListener {

    private OrganisationEventsView          view;
    private OrganisationEventsInteractor    interactor;

    public OrganisationEventsPresenter(OrganisationEventsView view, Network network, int organisationId) {
        this.view = view;
        interactor = new OrganisationEventsInteractor(view.getActivity().getApplicationContext(), network, organisationId);
    }

    @Override
    public void getEvents() {
        view.showProgress();
        interactor.getEvents(this);
    }

    @Override
    public void navigateToEventView(Event event) {
        ((MainActivity) view.getActivity()).replaceView(OrganisationEventView.newInstance(event.getId(), event.getTitle()), Animation.FADE_IN_OUT);
    }

    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
        view.setDialogError(title, msg);
    }

    @Override
    public void onSuccess(List<Event> events) {
        view.updateRecyclerView(events);
        view.hideProgress();
    }
}
