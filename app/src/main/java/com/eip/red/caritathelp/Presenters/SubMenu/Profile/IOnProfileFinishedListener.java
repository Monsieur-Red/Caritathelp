package com.eip.red.caritathelp.Presenters.SubMenu.Profile;

import com.eip.red.caritathelp.Models.Friends.Friend;
import com.eip.red.caritathelp.Models.Profile.MainPicture;
import com.eip.red.caritathelp.Models.User.User;

import java.util.List;

/**
 * Created by pierr on 11/05/2016.
 */

public interface IOnProfileFinishedListener {

    void onDialog(String title, String msg);

    void onFailureInitProfileImg();

    void onSuccessUploadProfileImg();

    void onSuccessGetProfile(User user, List<Friend> friend);

    void onSuccessGetNews();

    void onSuccessAddFriend(String name);
}
