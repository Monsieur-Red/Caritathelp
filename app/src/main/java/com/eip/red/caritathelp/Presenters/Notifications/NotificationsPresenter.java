package com.eip.red.caritathelp.Presenters.Notifications;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Activities.Main.MyNavigationBottomBar;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Notifications.Notification;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Views.Notifications.NotificationsRVAdapter;
import com.eip.red.caritathelp.Views.Notifications.NotificationsView;
import com.eip.red.caritathelp.Views.SubMenu.Friends.MyFriendsRVAdpater;

import java.util.List;

/**
 * Created by pierr on 21/04/2016.
 */

public class NotificationsPresenter implements INotificationsPresenter, IOnNotificationsFinishedListener {

    private NotificationsView       view;
    private NotificationsInteractor interactor;


    public NotificationsPresenter(NotificationsView view, Network network, User user) {
        this.view = view;
        interactor = new NotificationsInteractor(view.getContext(), network, user);
    }


    @Override
    public void getNotifications() {
        view.showProgress();
        interactor.getNotifications(view.getProgressBar(), this);
    }

    @Override
    public void friendshipReply(final Notification notification) {
        final IOnNotificationsFinishedListener    listener = this;
        final String  name = notification.getFirstname() + " " + notification.getLastname();

        // Display Dialog Box
        new AlertDialog.Builder(view.getContext())
                .setCancelable(true)
                .setTitle("Demande d'amis")
                .setMessage("Acceptez-vous la demande d'amis de " + name + " ?")
                .setNegativeButton("Rejeter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        view.showProgress();
                        interactor.friendshipReply(notification.getNotif_id(), name, "false", view.getProgressBar(), listener);
                    }
                })
                .setPositiveButton("Accepter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        view.showProgress();
                        interactor.friendshipReply(notification.getNotif_id(), name, "true", view.getProgressBar(), listener);
                    }
                })
                .show();
    }

    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
        view.setDialog(title, msg);
    }

    @Override
    public void onSuccessGetNotification(List<Notification> notifications) {
        // Set RecyclerView
        NotificationsRVAdapter adpater = view.getRvAdapter();
        adpater.update(notifications);

        // Set Notification Number Bottom Navigation Bar
        int number = adpater.getItemCount();
        ((MainActivity) view.getActivity()).getMyNavigationBottomBar().setNotifications(number, MyNavigationBottomBar.NOTIFICATIONS);

        // Set ProgressBar Visibility
        view.hideProgress();
    }

    @Override
    public void onSuccessFriendshipReply(String name, String acceptance) {
        // Set ProgressBar Visibility
        view.hideProgress();

        // Display Dialog Success Msg
        String msg;

        if (acceptance.equals("true"))
            msg = name + " a été ajouté dans votre liste d'amis.";
        else
            msg = "La demande de " + name + " a été rejeté.";

        new AlertDialog.Builder(view.getContext())
                .setCancelable(true)
                .setTitle("Demande d'amis")
                .setMessage(msg)
                .show();

        // Update the notifications RV
        getNotifications();
    }
}
