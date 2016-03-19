package com.eip.red.caritathelp.Presenters.Organisation.Events.Event;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Organisation.Events.Event.Informations.OrganisationEventInformationsView;
import com.eip.red.caritathelp.Views.Organisation.Events.Event.OrganisationEventView;

/**
 * Created by pierr on 18/03/2016.
 */

public class OrganisationEventPresenter implements IOrganisationEventPresenter {

    private OrganisationEventView           view;
    private OrganisationEventInteractor    interactor;

    public OrganisationEventPresenter(OrganisationEventView view, Network network, int eventId) {
        this.view = view;
        interactor = new OrganisationEventInteractor(view.getActivity().getApplicationContext(), network, eventId);
    }

    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.btn_management:
                break;
            case R.id.btn_members:
                break;
            case R.id.btn_informations:
                ((MainActivity) view.getActivity()).replaceView(OrganisationEventInformationsView.newInstance(interactor.getEventId()), Animation.FLIP_LEFT_RIGHT);
                break;
        }
    }

    @Override
    public void getNews() {

    }
}
