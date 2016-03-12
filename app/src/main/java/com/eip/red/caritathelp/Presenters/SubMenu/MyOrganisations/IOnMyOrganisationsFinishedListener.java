package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations;

import com.eip.red.caritathelp.Models.Organisation;

import java.util.List;

/**
 * Created by pierr on 24/02/2016.
 */

public interface IOnMyOrganisationsFinishedListener {

    void onDialogError(String title, String msg);

    void onSuccess(List<Organisation> myOrganisationsNames);
}
