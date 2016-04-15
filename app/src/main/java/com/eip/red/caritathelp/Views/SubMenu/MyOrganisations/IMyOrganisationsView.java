package com.eip.red.caritathelp.Views.SubMenu.MyOrganisations;

import com.eip.red.caritathelp.Models.Organisation.Organisation;

import java.util.List;

/**
 * Created by pierr on 24/02/2016.
 */

public interface IMyOrganisationsView {

    void showProgress();

    void hideProgress();

    void setDialogError(String title, String msg);

    void updateRecyclerView(List<Organisation> myOrganisationsOwner, List<Organisation> myOrganisationsMember);

}
