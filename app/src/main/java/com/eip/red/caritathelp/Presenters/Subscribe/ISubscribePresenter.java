package com.eip.red.caritathelp.Presenters.Subscribe;


import java.util.List;

/**
 * Created by pierr on 09/12/2015.
 */

public interface ISubscribePresenter {

    boolean goToSecondPage(String firstName, String lastName, String gender, List<Integer> birthday);

    boolean goToThirdPage(String mail, String password, String passwordChecking);

    void subscribe(boolean geolocation, boolean notification, boolean terms);

}
