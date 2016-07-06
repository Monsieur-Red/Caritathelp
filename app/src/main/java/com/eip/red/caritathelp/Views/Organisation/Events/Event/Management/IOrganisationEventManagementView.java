package com.eip.red.caritathelp.Views.Organisation.Events.Event.Management;

import com.eip.red.caritathelp.Models.Home.News;

import java.util.List;

/**
 * Created by pierr on 14/04/2016.
 */

public interface IOrganisationEventManagementView {

    void showProgress();

    void hideProgress();

    void setTitleError(String error);

    void setPhotoError(String error);

    void setBeginDateError(String error);

    void setEndDateError(String error);

    void setLocationError(String error);

    void setDescriptionError(String error);

    void setDialog(String title, String msg);
}
