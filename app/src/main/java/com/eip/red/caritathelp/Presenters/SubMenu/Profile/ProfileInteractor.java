package com.eip.red.caritathelp.Presenters.SubMenu.Profile;

import android.content.Context;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Models.Friends.Friends;
import com.eip.red.caritathelp.Models.Friendship;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Profile.MainPicture;
import com.eip.red.caritathelp.Models.Profile.MainPictureJson;
import com.eip.red.caritathelp.Models.User.User;
import com.eip.red.caritathelp.Models.User.UserJson;
import com.eip.red.caritathelp.R;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by pierr on 11/05/2016.
 */

public class ProfileInteractor {

    private Context context;
    private User    mainUser;
    private int     userId;

    public ProfileInteractor(Context context, User mainUser, int userId) {
        this.context = context;
        this.mainUser = mainUser;
        this.userId = userId;
    }

    // First Get the URL of the image
    // Then Load the image
    public void initProfileImg(final CircularImageView imageView, final IOnProfileFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", mainUser.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_VOLUNTEERS + userId + Network.API_REQUEST_VOLUNTEERS_MAIN_PICTURE)
                .setJsonObjectBody(json)
                .as(new TypeToken<MainPictureJson>() {})
                .setCallback(new FutureCallback<MainPictureJson>() {
                    @Override
                    public void onCompleted(Exception error, MainPictureJson result) {
                        if (error == null) {
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialog("Statut 400", result.getMessage());
                            else {
                                MainPicture picture = result.getResponse();

                                if (picture == null)
                                    listener.onFailureInitProfileImg();
                                else
                                    loadImage(imageView, picture.getPicture_path().getUrl());

                            }
                        }
                        else
                            listener.onDialog("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    private void loadImage(CircularImageView imageView, String url) {
        Picasso.with(context)
                .load(Network.API_LOCATION_2 + url)
                .noPlaceholder()
                .error(R.drawable.profile_example)
                .into(imageView);
    }

    public void uploadProfileImg(String file, String filename, String originFilename, int assocId, int eventId, String isMain, final IOnProfileFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", mainUser.getToken());
        json.addProperty("file", file);
        json.addProperty("filename", filename);
        json.addProperty("original_filename", originFilename);
        json.addProperty("is_main", isMain);

        if (assocId != -1)
            json.addProperty("assoc_id", assocId);

        if (eventId != -1)
            json.addProperty("event_id", eventId);

        Ion.with(context)
                .load("POST", Network.API_LOCATION_2 + Network.API_REQUEST_PICTURES)
                .setJsonObjectBody(json)
                .as(new TypeToken<MainPictureJson>() {})
                .setCallback(new FutureCallback<MainPictureJson>() {
                    @Override
                    public void onCompleted(Exception error, MainPictureJson result) {
                        if (error == null) {
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialog("Statut 400", result.getMessage());
                            else {
                                // Set User Model
                                mainUser.setPicture(result.getResponse());

                                listener.onSuccessUploadProfileImg();
                            }
                        }
                        else
                            listener.onDialog("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    public void getProfile(final IOnProfileFinishedListener listener, final ProgressBar progressBar) {
        JsonObject json = new JsonObject();

        json.addProperty("token", mainUser.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_VOLUNTEERS + userId)
                .progressBar(progressBar)
                .setJsonObjectBody(json)
                .as(new TypeToken<UserJson>() {})
                .setCallback(new FutureCallback<UserJson>() {
                    @Override
                    public void onCompleted(Exception error, UserJson result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialog("Statut 400", result.getMessage());
                            else {
                                // Check if it's MY profile
                                if (userId == mainUser.getId())
                                    listener.onSuccessGetProfile(result.getResponse(), null);
                                else
                                    getMyFriends(listener, progressBar, result.getResponse());
                            }
                        }
                        else
                            listener.onDialog("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    // Get MAIN user's Friends! Not friends to the user who the profile is shown.
    private void getMyFriends(final IOnProfileFinishedListener listener, final ProgressBar progressBar, final User user) {
        JsonObject json = new JsonObject();

        json.addProperty("token", mainUser.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_FRIENDSHIP_VOLUNTEER + mainUser.getId() + Network.API_REQUEST_FRIENDSHIP)
                .progressBar(progressBar)
                .setJsonObjectBody(json)
                .as(new TypeToken<Friends>(){})
                .setCallback(new FutureCallback<Friends>() {
                    @Override
                    public void onCompleted(Exception error, Friends result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialog("Statut 400", result.getMessage());
                            else
                                listener.onSuccessGetProfile(user, result.getResponse());
                        }
                        else
                            listener.onDialog("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    public void addFriend(final IOnProfileFinishedListener listener, ProgressBar progressBar, final String name) {
        JsonObject json = new JsonObject();

        json.addProperty("token", mainUser.getToken());
        json.addProperty("volunteer_id", String.valueOf(userId));

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

    public User getMainUser() {
        return mainUser;
    }

    public boolean isMainUser() {
        return (mainUser.getId() == userId);
    }

    public int getMainUserId() {
        return mainUser.getId();

    }
    public int getUserId() {
        return userId;
    }
}
