package com.eip.red.caritathelp.Presenters.SubMenu.AccountSettings;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User.User;
import com.eip.red.caritathelp.Views.SubMenu.AccountSettings.AccountSettingsView;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.HashMap;

/**
 * Created by pierr on 22/01/2016.
 */
public class AccountSettingsInteractor {

    private Context     context;
    private User        user;

    public AccountSettingsInteractor(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    public void saveModification(final HashMap<Integer, EditText> modification, final IOnAccountSettingsFinishedListener listener) {
        if (getNumberOfModification(modification) > 0 && !simpleVerification(modification, listener))
            apiRequest(modification, listener);
    }

    private int getNumberOfModification(final HashMap<Integer, EditText> modification) {
        int number = 0;

        final String        firstname = modification.get(AccountSettingsView.FIRSTNAME).getText().toString();
        final String        lastname = modification.get(AccountSettingsView.LASTNAME).getText().toString();
        final String        mail = modification.get(AccountSettingsView.MAIL).getText().toString();
        final String        password = modification.get(AccountSettingsView.PASSWORD_NEW).getText().toString();

        if (!TextUtils.isEmpty(mail) && !mail.equals(user.getMail()))
            number++;

        if (!TextUtils.isEmpty(firstname) && !firstname.equals(user.getFirstname()))
            number++;

        if (!TextUtils.isEmpty(lastname) && !lastname.equals(user.getLastname()))
            number++;

        if (!TextUtils.isEmpty(password))
            number++;

        return (number);
    }

    private boolean simpleVerification(final HashMap<Integer, EditText> modification, final IOnAccountSettingsFinishedListener listener) {
        boolean     error = false;
        String      password_new = modification.get(AccountSettingsView.PASSWORD_NEW).getText().toString();
        String      password_new_checking = modification.get(AccountSettingsView.PASSWORD_NEW_CHECKING).getText().toString();

        if(!password_new.equals(password_new_checking)) {
            listener.onNewPasswordCheckingError("Mot de passe différent");
            error = true;
        }

        return (error);
    }

    private void apiRequest(final HashMap<Integer, EditText> modification, final IOnAccountSettingsFinishedListener listener) {
        JsonObject          json = new JsonObject();
        final String        firstname = modification.get(AccountSettingsView.FIRSTNAME).getText().toString();
        final String        lastname = modification.get(AccountSettingsView.LASTNAME).getText().toString();
        final String        mail = modification.get(AccountSettingsView.MAIL).getText().toString();
        final String        password = modification.get(AccountSettingsView.PASSWORD_NEW).getText().toString();

        json.addProperty("token", user.getToken());

        if (!TextUtils.isEmpty(mail) && !mail.equals(user.getMail()))
            json.addProperty("mail", mail);

        if (!TextUtils.isEmpty(firstname) && !firstname.equals(user.getFirstname()))
            json.addProperty("firstname", firstname);

        if (!TextUtils.isEmpty(lastname) && !lastname.equals(user.getLastname()))
            json.addProperty("lastname", lastname);

        if (!TextUtils.isEmpty(password))
            json.addProperty("password", password);

        Ion.with(context)
                .load("PUT", Network.API_LOCATION + Network.API_REQUEST_ACCOUNT_SETTINGS_MODIFICATION + String.valueOf(user.getId()))
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error != null) {
                            if (result.get(Network.API_PARAMETER_STATUS).getAsInt() == Network.API_STATUS_ERROR)
                                listener.onEmailError(result.get("message").getAsString());
                            else {
                                // Update User Model
                                if (!TextUtils.isEmpty(mail) && !mail.equals(user.getMail()))
                                    user.setMail(mail);

                                if (!TextUtils.isEmpty(firstname) && !firstname.equals(user.getFirstname()))
                                    user.setFirstname(firstname);

                                if (!TextUtils.isEmpty(lastname) && !lastname.equals(user.getLastname()))
                                    user.setLastname(lastname);

                                listener.onSuccess();
                            }
                        }
                    }
                });
    }

}
