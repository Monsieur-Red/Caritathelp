package com.eip.red.caritathelp.Presenters.SubMenu.MyEvents;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.Views.Organisation.Events.Event.OrganisationEventView;
import com.eip.red.caritathelp.Views.SubMenu.MyEvents.MyEventsView;

import java.util.List;

/**
 * Created by pierr on 18/03/2016.
 */
public class MyEventsPresenter implements IMyEventPresenter, IOnMyEventsFinishedListener {

    private MyEventsView        view;
    private MyEventsInteractor  interactor;

    public MyEventsPresenter(MyEventsView view, Network network, int userId) {
        this.view = view;

        interactor = new MyEventsInteractor(view.getActivity().getApplicationContext(), network, userId);
    }

    @Override
    public void getMyEvents() {
        view.showProgress();
        interactor.getMyEvents(this);
    }

    @Override
    public void navigateToEventView(Event event) {
        /* Go To Event Page */
        ((MainActivity) view.getActivity()).replaceView(OrganisationEventView.newInstance(event.getId(), event.getTitle()), Animation.FADE_IN_OUT);
    }

    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
        view.setDialogError(title, msg);
    }

    @Override
    public void onSuccess(List<Event> events) {
        view.hideProgress();
        view.updateRecyclerView(events);
    }
}
