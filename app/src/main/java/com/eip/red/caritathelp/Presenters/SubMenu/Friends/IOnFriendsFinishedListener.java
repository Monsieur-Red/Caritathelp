package com.eip.red.caritathelp.Presenters.SubMenu.Friends;

import com.eip.red.caritathelp.Models.Friends.Friend;

import java.util.List;

/**
 * Created by pierr on 19/04/2016.
 */

public interface IOnFriendsFinishedListener {

    void onDialog(String title, String msg);

    void onSuccessGetMyFriends(List<Friend> friends);

    void onSuccessGetInvitations();

    void onSuccessGetSent();

    void onSuccessBlockFriend();

    void onSuccessRemoveFriend();
}
