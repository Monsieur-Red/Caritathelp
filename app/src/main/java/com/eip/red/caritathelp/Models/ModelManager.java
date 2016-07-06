package com.eip.red.caritathelp.Models;

import android.content.Intent;

import com.eip.red.caritathelp.Models.Home.NewsList;
import com.eip.red.caritathelp.Models.User.User;

import java.io.Serializable;

/**
 * Created by pierr on 11/01/2016.
 */

public class ModelManager implements Serializable {

    private User user;
    private NewsList    newsList;

    public ModelManager(Intent intent) {
        // METHOD WITHOUT INTERNET CONNTECTION
//        this.user = new User();
//        this.network = new Network();

        // RIGHT METHOD
        this.user =  (User) intent.getSerializableExtra("user");
        newsList = new NewsList();
    }

    public User getUser() {
        return (user);
    }

    public NewsList getNewsList() {
        return newsList;
    }
}
