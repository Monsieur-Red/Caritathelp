package com.eip.red.caritathelp.Presenters.SubMenu.Friends;

import com.eip.red.caritathelp.Models.Friends.Friend;

/**
 * Created by pierr on 19/04/2016.
 */

public interface IFriendsPresenter {

    void onClick(int viewId);

    void getMyFriends(boolean isSwipeRefresh);

    void getInvitations();

    void getSent();

    void navigateToFriendView(int friendId);

    void blockFriend(int friendId);

    void removeFriend(Friend friend);
}
