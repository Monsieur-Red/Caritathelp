package com.eip.red.caritathelp.Presenters.Subscribe;

import android.content.Context;
import android.text.TextUtils;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

/**
 * Created by pierr on 09/12/2015.
 */

public class SubscribeInteractor {

    static final private String     ERROR_MANDATORY = "Ce champ est obligatoire";
    static final private String     ERROR_PASSWORD_CHECKING = "Mot de passe différent";
    static final private String     ERROR_TERMS = "Les termes et conditions doivent être acceptés.";

    private Context context;
    private User    user;

    public SubscribeInteractor(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    public boolean goToSecondPage(String firstName, String lastName, String gender, List birthday, IOnSubscribeFinishedListener listener) {
        int error = 0;

        // Check Empty FirstName edittext
        if (TextUtils.isEmpty(firstName)) {
            listener.onFirstNameError(ERROR_MANDATORY);
            error = 1;
        }

        // Check Empty LastName edittext
        if (TextUtils.isEmpty(lastName)) {
            listener.onLastNameError(ERROR_MANDATORY);
            error = 1;
        }

        // Check Birthday errors
        if (checkBirthdayErrors(birthday, listener))
            error = 1;

        // Check Error
        if (error == 1)
            return (false);

        // Set User Model
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setBirthday(birthday);

        return (true);
    }

    private boolean checkBirthdayErrors(List<Integer> birthdayList, IOnSubscribeFinishedListener listener) {
        int     error = 0;

        // Check Birthday Errors
        if (!birthdayList.isEmpty()) {
            int     year = birthdayList.get(0);
            int     month = birthdayList.get(1);
            int     day = birthdayList.get(2);
            String  birthday = getBirthdayStr(year, month, day);

            // Set Birthday date in the User Model
            user.setBirthday(birthday);
        }
        else {
            // Set the view
            listener.onBirthdayError(ERROR_MANDATORY);
            error = 1;
        }

        if (error == 1)
            return (true);
        return (false);
    }

//    private boolean isFutureDate(int year, int month, int day) {
//        Calendar    calendar = Calendar.getInstance();
//        int         currentYear = calendar.get(Calendar.YEAR);
//        int         currentMonth = calendar.get(Calendar.MONTH) + 1;
//        int         currentDay = calendar.get(Calendar.DAY_OF_MONTH);
//
//        if (year > currentYear)
//            return (true);
//        else if (year == currentYear && month > currentMonth)
//            return (true);
//        else if (year == currentYear && month == currentMonth && day > currentDay)
//            return (true);
//
//        return (false);
//    }

    private String getBirthdayStr(int year, int month, int day) {
        String birthday = "";

        // Init Day
        if (day < 10)
            birthday += "0" + String.valueOf(day);
        else
            birthday += String.valueOf(day);

        // Init Month
        if (month < 10)
            birthday += "/0" + String.valueOf(month);
        else
            birthday += "/" + String.valueOf(month);

        // Init Year
        birthday += "/" + String.valueOf(year);

        return (birthday);
    }


    public boolean goToThirdPage(String mail, String password, String passwordChecking, IOnSubscribeFinishedListener listener) {
        int error = 0;

        // Check Mail
        if (TextUtils.isEmpty(mail)) {
            listener.onEmailError(ERROR_MANDATORY);
            error = 1;
        }
        else
            user.setMail(mail);


        // Check Password
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError(ERROR_MANDATORY);
            error = 1;
        }

        // Check Password Checking
        if (TextUtils.isEmpty(passwordChecking)) {
            listener.onPasswordCheckingError(ERROR_MANDATORY);
            error = 1;
        }
        else if (!password.equals(passwordChecking)) {
            listener.onPasswordCheckingError(ERROR_PASSWORD_CHECKING);
            error = 1;
        }
        else
            user.setPassword(password);

        if (error == 0)
            return (true);
        return (false);
    }

    public void subscribe(boolean geolocation, boolean notification, boolean terms, IOnSubscribeFinishedListener listener) {
        if (terms) {
            // Set the User Model
            user.setGeolocation(geolocation);
            user.setNotifications(notification);

            // SEND REQUEST
            subscribeRequest(listener);
        }
        else
            listener.onTermsError(ERROR_TERMS);
    }

    private void subscribeRequest(final IOnSubscribeFinishedListener listener) {
        JsonObject  json = new JsonObject();

        json.addProperty("mail", user.getMail());
        json.addProperty("password", user.getPassword());
        json.addProperty("firstname", user.getFirstName());
        json.addProperty("lastname", user.getLastName());
        json.addProperty("birthday", user.getBirthday());
        json.addProperty("gender", user.getGender());
        json.addProperty("allowgps", user.isGeolocation());

        Ion.with(context)
                .load("POST", Network.API_LOCATION + Network.API_REQUEST_SUBSCRIBE)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            if (result.get(Network.API_PARAMETER_STATUS).getAsInt() == Network.API_STATUS_ERROR) {
                                if (result.get(Network.API_PARAMETER_MSG).getAsString().equals(Network.API_MSG_INVALID_MAIL)) {
                                    listener.onEmailError("Page 3 : Mail invalide");
                                    listener.onSubscribeError("Page 3 : Mail invalide");
                                } else if (result.get(Network.API_PARAMETER_MSG).getAsString().equals(Network.API_MSG_UNAVAILABLE_MAIL)) {
                                    listener.onEmailError("Page 3 : Mail déjà utilisé");
                                    listener.onSubscribeError("Page 3 : Mail déjà utilisé");
                                }
                            } else
                                listener.onSuccess(new Network(result));
                        }
                    }
                    });
    }

}

// API REQUEST RESULT
// RESULT: {"status":400,"message":"Validation failed: Mail is invalid","response":null}
// RESULT: {"status":400,"message":"Unavailable mail","response":null}
// RESULT: {"status":200,"message":"ok","response":{"id":4,"mail":"test@test.com","token":"XWJXx-SHUUZb6fAcxyX-NA","firstname":"aha","lastname":"ahah","birthday":"1992-11-25","gender":null,"city":null,"latitude":null,"longitude":null,"allowgps":false,"notifications":{"add_friend":[]}}}
