package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations;

/**
 * Created by pierr on 24/02/2016.
 */

public interface IOnMyOrganisationsFinishedListener {

    void onConnectionInternetError(String error);

    void onSuccess();
}
