package com.eip.red.caritathelp.Presenters.OrganisationSearch;

import com.eip.red.caritathelp.Models.Organisation.Organisation;

/**
 * Created by pierr on 25/02/2016.
 */

public interface IOrganisationSearchPresenter {

    void getAllOrganisations();

    void goToOrganisationView(Organisation organisation);
}
