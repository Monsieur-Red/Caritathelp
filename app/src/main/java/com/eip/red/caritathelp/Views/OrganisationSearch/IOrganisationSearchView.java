package com.eip.red.caritathelp.Views.OrganisationSearch;

import com.eip.red.caritathelp.Models.Organisation;

import java.util.List;

/**
 * Created by pierr on 25/02/2016.
 */

public interface IOrganisationSearchView {

    void showProgress();

    void hideProgress();

    void setDialogError(String title, String msg);

    void updateListView(List<String> organisations);

}
