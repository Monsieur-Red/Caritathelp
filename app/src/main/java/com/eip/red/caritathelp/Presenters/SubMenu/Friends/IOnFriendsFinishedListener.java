package com.eip.red.caritathelp.Presenters.SubMenu.Friends;

/**
 * Created by pierr on 19/04/2016.
 */

public interface IOnFriendsFinishedListener {

    void onDialog(String title, String msg);

    void onSuccessGetMyFriends();

    void onSuccessGetInvitations();

    void onSuccessGetSent();
}
