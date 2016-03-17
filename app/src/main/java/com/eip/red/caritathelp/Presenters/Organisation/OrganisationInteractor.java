package com.eip.red.caritathelp.Presenters.Organisation;

import android.content.Context;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;

/**
 * Created by pierr on 11/03/2016.
 */

public class OrganisationInteractor {

    private Context         context;
    private Network         network;
    private Organisation    organisation;

    public OrganisationInteractor(Context context, Network network, Organisation organisation) {
        this.context = context;
        this.network = network;
        this.organisation = organisation;
    }

    public Organisation getOrganisation() {
        return (organisation);
    }

    public String getOrganisationName() {
        return (organisation.getName());
    }

    public int getOrganisationId() {
        return (organisation.getId());
    }

}
