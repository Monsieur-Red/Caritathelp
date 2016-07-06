package com.eip.red.caritathelp.Presenters.SubMenu.Friends;

import com.eip.red.caritathelp.Models.Friends.Friend;
import com.eip.red.caritathelp.Models.Friends.FriendInvitation;

/**
 * Created by pierr on 19/04/2016.
 */

public interface IFriendsPresenter {

    void onClick(int viewId);

    void onClick(int viewId, Friend friend);

    void onClick(int viewId, FriendInvitation friendInvitation);

    void getMyFriends(boolean isSwipeRefresh);

    void getInvitations();

    void getSent();

    void navigateToFriendProfile(int friendId);
}
