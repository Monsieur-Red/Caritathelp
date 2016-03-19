package com.eip.red.caritathelp.Views.Organisation.Events.Event.Informations;

/**
 * Created by pierr on 18/03/2016.
 */

public interface IOrganisationEventInformationsView {

    void showProgress();

    void hideProgress();

    void setDialogError(String title, String msg);

    void setViewData(String dateBegin, String dateEnd, String location, String description);
}
