package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations.OrganisationCreation;

import android.content.Context;
import android.text.TextUtils;

import com.eip.red.caritathelp.Models.Network;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 24/02/2016.
 */

public class OrganisationCreationInteractor {

    static final private String     ERROR_MANDATORY = "Ce champ est obligatoire";

    private Context     context;
    private Network     network;

    public OrganisationCreationInteractor(Context context, Network network) {
        this.context = context;
        this.network = network;
    }

    public void create(String name, String theme, String city, String description, IOnOrganisationCreationsFinishedListener listener) {
        if (!checkError(name, theme, city, description, listener))
            apiRequest(name, theme, city, description, listener);
    }

    private boolean checkError(String name, String theme, String city, String description, IOnOrganisationCreationsFinishedListener listener) {
        boolean error = false;

        // Check Organisation Name
        if (TextUtils.isEmpty(name)) {
            listener.onNameError(ERROR_MANDATORY);
            error = true;
        }

        // Check Organisation Theme
        if (TextUtils.isEmpty(theme)) {
            listener.onThemeError(ERROR_MANDATORY);
            error = true;
        }

        // Check Organisation City
        if (TextUtils.isEmpty(city)) {
            listener.onCityError(ERROR_MANDATORY);
            error = true;
        }

        // Check Organisation Description
        if (TextUtils.isEmpty(description)) {
            listener.onDescriptionError(ERROR_MANDATORY);
            error = true;
        }

        return (error);
    }

    private void apiRequest(final String name, String theme, String city, String description, final IOnOrganisationCreationsFinishedListener listener) {
        JsonObject          json = new JsonObject();

        json.addProperty("token", network.getToken());
        json.addProperty("name", name);
        json.addProperty("city", city);

        if (!TextUtils.isEmpty(description))
            json.addProperty("description", description);

        Ion.with(context)
                .load("POST", Network.API_LOCATION + Network.API_REQUEST_ORGANISATION)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            if (result.get(Network.API_PARAMETER_STATUS).getAsInt() == Network.API_STATUS_ERROR) {
                                if (result.get(Network.API_PARAMETER_MSG).getAsString().equals("Unavailable name"))
                                    listener.onDialogError("Association existante", "Veuillez modifier le nom de l'association");
                            }
                            else
                                listener.onSuccess(name);
//                            if (result.get(Network.API_PARAMETER_STATUS).getAsInt() == Network.API_STATUS_ERROR)
//                                listener.onEmailError(result.get("message").getAsString());
//                            else {
//                                // Update User Model
//                                if (!TextUtils.isEmpty(mail) && !mail.equals(user.getMail()))
//                                    user.setMail(mail);
//
//                                if (!TextUtils.isEmpty(firstname) && !firstname.equals(user.getFirstName()))
//                                    user.setFirstName(firstname);
//
//                                if (!TextUtils.isEmpty(lastname) && !lastname.equals(user.getLastName()))
//                                    user.setLastName(lastname);
//
//                                listener.onSuccess();
//                            }
//                        }
                        } else
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }
}
