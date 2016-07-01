package com.eip.red.caritathelp.Views.SubMenu.Friends;

import com.eip.red.caritathelp.Models.Friends.Invitation;
import com.eip.red.caritathelp.Presenters.SubMenu.Friends.FriendsPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 01/07/2016.
 */

public class InvitationsRVAdapter {

    private FriendsPresenter    presenter;
    private List<Invitation>    invitations;

    public InvitationsRVAdapter(FriendsPresenter presenter) {
        this.presenter = presenter;
        invitations = new ArrayList<>();
    }



}
