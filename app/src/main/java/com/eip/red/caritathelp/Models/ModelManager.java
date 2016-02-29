package com.eip.red.caritathelp.Models;

import android.content.Intent;

import com.eip.red.caritathelp.Models.Home.NewsList;

/**
 * Created by pierr on 11/01/2016.
 */

public class ModelManager {

    private User        user;
    private Network     network;
    private NewsList    newsList;

    public ModelManager(Intent intent) {
        // METHOD WITHOUT INTERNET CONNTECTION
//        this.user = new User();
//        this.network = new Network();

        // RIGHT METHOD
        this.user =  (User) intent.getSerializableExtra("user");
        this.network = (Network) intent.getSerializableExtra("network");
        newsList = new NewsList();
    }

    public User getUser() {
        return (user);
    }

    public Network  getNetwork() {
        return (network);
    }

    public NewsList getNewsList() {
        return newsList;
    }
}
