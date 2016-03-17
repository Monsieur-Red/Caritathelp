package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations;

import com.eip.red.caritathelp.Models.Organisation.Organisation;

/**
 * Created by pierr on 24/02/2016.
 */

public interface IMyOrganisationsPresenter {

    void onClick(int viewId);

    void getMyOrganisations();

    void goToOrganisationView(Organisation organisation);
}
