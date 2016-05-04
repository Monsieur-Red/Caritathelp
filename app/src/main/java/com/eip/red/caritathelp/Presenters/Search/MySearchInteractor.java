package com.eip.red.caritathelp.Presenters.Search;

import android.content.Context;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Models.Friendship;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisations;
import com.eip.red.caritathelp.Models.Search.Volunteer;
import com.eip.red.caritathelp.Models.Search.Volunteers;
import com.eip.red.caritathelp.Models.User;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 21/04/2016.
 */

public class MySearchInteractor {

    private Context context;
    private Network network;
    private User    user;

    public MySearchInteractor(Context context, Network network, User user) {
        this.context = context;
        this.network = network;
        this.user = user;
    }

    public void getQueryTextSubmit(String query) {

    }

    public void getQueryTextChange(String query, ProgressBar progressBar, final IOnMySearchFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());
        json.addProperty("research", query);

        Ion.with(context)
                .load("GET", Network.API_LOCATION + "/volunteers/search" )
                .progressBar(progressBar)
                .setJsonObjectBody(json)
                .as(new TypeToken<Volunteers>(){})
                .setCallback(new FutureCallback<Volunteers>() {
                    @Override
                    public void onCompleted(Exception error, Volunteers result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialog("Statut 400", result.getMessage());
                            else
                                listener.onSuccessQueryTextChange(result.getResponse());
                        }
                        else
                            listener.onDialog("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    public void addFriend(int volunteerId, final String name, ProgressBar progressBar, final IOnMySearchFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());
        json.addProperty("volunteer_id", String.valueOf(volunteerId));

        Ion.with(context)
                .load("POST", Network.API_LOCATION + Network.API_REQUEST_FRIENDSHIP_ADD )
                .progressBar(progressBar)
                .setJsonObjectBody(json)
                .as(new TypeToken<Friendship>(){})
                .setCallback(new FutureCallback<Friendship>() {
                    @Override
                    public void onCompleted(Exception error, Friendship result) {
                        if (error == null) {
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialog("Statut 400", result.getMessage());
                            else
                                listener.onSuccessAddFriend(name);
                        }
                        else
                            listener.onDialog("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    public int getUserId() {
        return (user.getId());
    }
}
