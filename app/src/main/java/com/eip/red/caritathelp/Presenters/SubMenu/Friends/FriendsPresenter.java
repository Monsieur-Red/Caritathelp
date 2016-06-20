package com.eip.red.caritathelp.Presenters.SubMenu.Friends;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Friends.Friend;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User.User;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;
import com.eip.red.caritathelp.Views.SubMenu.Friends.FriendsView;
import com.eip.red.caritathelp.Views.SubMenu.Profile.ProfileView;

import java.util.List;

/**
 * Created by pierr on 19/04/2016.
 */

public class FriendsPresenter implements IFriendsPresenter, IOnFriendsFinishedListener {

    private FriendsView         view;
    private FriendsInteractor   interactor;

    public FriendsPresenter(FriendsView view, User mainUser, int userId) {
        this.view = view;
        interactor = new FriendsInteractor(view.getContext(), mainUser, userId);
    }

    @Override
    public void onClick(int viewId) {
        if (viewId == R.id.btn_add)
            ((MainActivity) view.getActivity()).getMySearchView().show();
    }

    @Override
    public void getMyFriends(boolean isSwipeRefresh) {
        if (!isSwipeRefresh)
            view.showProgress();

        interactor.getMyFriends(view.getProgressBar(), this);
    }

    @Override
    public void getInvitations() {

    }

    @Override
    public void getSent() {

    }

    @Override
    public void navigateToFriendProfile(int friendId) {
        Tools.replaceView(view, ProfileView.newInstance(friendId), Animation.FADE_IN_OUT, false);
    }

    @Override
    public void blockFriend(int friendId) {

    }

    @Override
    public void removeFriend(final Friend friend) {
        final IOnFriendsFinishedListener  listener = this;
        String  name = friend.getFirstname() + " " + friend.getLastname();

        // Display RemoveFriendDialog
        new AlertDialog.Builder(view.getContext())
                .setCancelable(true)
                .setTitle("Supprimer un ami")
                .setMessage("Êtes-vous sûr de vouloir supprimer " + name + " de votre liste d'amis ?")
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        view.showProgress();
                        interactor.removeFriend(friend.getId(), view.getProgressBar(), listener);
                    }
                })
                .show();
    }

    @Override
    public void onDialog(String title, String msg) {
        view.hideProgress();
        view.setDialog(title, msg);
    }

    @Override
    public void onSuccessGetMyFriends(List<Friend> friends) {
        // Set RecyclerView
        view.getAdapter().update(friends);

        // Set ProgressBar Visibility
        view.hideProgress();

        // Set SwipeRefreshLayout Refreshing
        view.getSwipeRefreshLayout().setRefreshing(false);
    }


    @Override
    public void onSuccessGetInvitations() {

    }

    @Override
    public void onSuccessGetSent() {

    }

    @Override
    public void onSuccessBlockFriend() {

    }

    @Override
    public void onSuccessRemoveFriend() {
        // Set RecyclerView
        getMyFriends(false);

        // Set ProgressBar Visibility
        view.hideProgress();
    }
}
