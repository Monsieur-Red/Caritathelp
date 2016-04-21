package com.eip.red.caritathelp.Presenters.SubMenu.Friends;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.SubMenu.Friends.FriendsView;

/**
 * Created by pierr on 19/04/2016.
 */

public class FriendsPresenter implements IFriendsPresenter, IOnFriendsFinishedListener {

    private FriendsView view;

    public FriendsPresenter(FriendsView view) {
        this.view = view;
    }

    @Override
    public void onClick(int viewId) {
        if (viewId == R.id.btn_add)
            ((MainActivity) view.getActivity()).getMySearchView().show();
    }

    @Override
    public void getMyFriends() {

    }

    @Override
    public void getInvitations() {

    }

    @Override
    public void getSent() {

    }

    @Override
    public void onDialog(String title, String msg) {

    }

    @Override
    public void onSuccessGetMyFriends() {

    }

    @Override
    public void onSuccessGetInvitations() {

    }

    @Override
    public void onSuccessGetSent() {

    }
}
