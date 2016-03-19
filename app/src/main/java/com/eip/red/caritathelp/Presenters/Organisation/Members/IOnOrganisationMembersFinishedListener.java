package com.eip.red.caritathelp.Presenters.Organisation.Members;

import com.eip.red.caritathelp.Models.Organisation.Member;

import java.util.List;

/**
 * Created by pierr on 11/03/2016.
 */
public interface IOnOrganisationMembersFinishedListener {

    void onDialogError(String title, String msg);

    void onSuccess(List<Member> members);
}
