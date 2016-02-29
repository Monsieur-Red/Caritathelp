package com.eip.red.caritathelp.Views.SubMenu.MyOrganisations;

/**
 * Created by pierr on 24/02/2016.
 */

public interface IMyOrganisationsView {

    void showProgress();

    void hideProgress();

    void setConnectionInternetError(String error);

    void updateListView();

}
