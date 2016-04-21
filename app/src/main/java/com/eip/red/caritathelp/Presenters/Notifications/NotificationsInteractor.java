package com.eip.red.caritathelp.Presenters.Notifications;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Models.Friendship;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Notifications.JsonNotifications;
import com.eip.red.caritathelp.Models.User;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 21/04/2016.
 */

public class NotificationsInteractor {

    private Context context;
    private Network network;
    private User    user;

    public NotificationsInteractor(Context context, Network network, User user) {
        this.context = context;
        this.network = network;
        this.user = user;
    }

    public void getNotifications(ProgressBar progressBar, final IOnNotificationsFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + "/volunteers/" +  user.getId() + "/notifications")
                .progressBar(progressBar)
                .setJsonObjectBody(json)
                .as(new TypeToken<JsonNotifications>(){})
                .setCallback(new FutureCallback<JsonNotifications>() {
                    @Override
                    public void onCompleted(Exception error, JsonNotifications result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialogError("Statut 400", result.getMessage());
                            else
                                listener.onSuccessGetNotification(result.getResponse().getAdd_friend());
                        }
                        else
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    public void friendshipReply(int notifId, final String name, final String acceptance, ProgressBar progressBar, final IOnNotificationsFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());
        json.addProperty("notif_id", String.valueOf(notifId));
        json.addProperty("acceptance", acceptance);

        Ion.with(context)
                .load("POST", Network.API_LOCATION + Network.API_REQUEST_FRIENDSHIP_REPLY)
                .progressBar(progressBar)
                .setJsonObjectBody(json)
                .as(new TypeToken<Friendship>(){})
                .setCallback(new FutureCallback<Friendship>() {
                    @Override
                    public void onCompleted(Exception error, Friendship result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialogError("Statut 400", result.getMessage());
                            else
                                listener.onSuccessFriendshipReply(name, acceptance);
                        }
                        else {
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                        }
                    }
                });

    }
}
