package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations.OrganisationCreation;

/**
 * Created by pierr on 24/02/2016.
 */

public interface IOnOrganisationCreationsFinishedListener {

    void onNameError(String error);

    void onThemeError(String error);

    void onCityError(String error);

    void onDescriptionError(String error);

    void onDialogError(String title, String msg);

    void onSuccess(String name);
}
