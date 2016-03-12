package com.eip.red.caritathelp.Views.Organisation.OrganisationMembers;

import com.eip.red.caritathelp.Models.Member;

import java.util.List;

/**
 * Created by pierr on 11/03/2016.
 */

public interface IOrganisationMembersView {

    void showProgress();

    void hideProgress();

    void setDialogError(String title, String msg);

    void updateListView(List<Member> members);
}
