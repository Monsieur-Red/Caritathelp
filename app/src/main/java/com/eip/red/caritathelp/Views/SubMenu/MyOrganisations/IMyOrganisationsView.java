package com.eip.red.caritathelp.Views.SubMenu.MyOrganisations;

import java.util.List;

/**
 * Created by pierr on 24/02/2016.
 */

public interface IMyOrganisationsView {

    void showProgress();

    void hideProgress();

    void setDialogError(String title, String msg);

    void updateListView(List<String> myOrganisationsNames);

}
