package com.eip.red.caritathelp.Presenters.OrganisationSearch;

import com.eip.red.caritathelp.Models.Organisation;

import java.util.List;

/**
 * Created by pierr on 25/02/2016.
 */

public interface IOnOrganisationSearchFinishedListener {

    void onDialogError(String title, String msg);

    void onSuccess(List<Organisation> organisations);
}
