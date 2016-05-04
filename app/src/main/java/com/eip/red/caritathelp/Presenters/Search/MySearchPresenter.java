package com.eip.red.caritathelp.Presenters.Search;

import android.app.AlertDialog;
import android.content.Context;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Search.Volunteer;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Views.Search.MySearchView;

import java.util.List;

/**
 * Created by pierr on 21/04/2016.
 */
public class MySearchPresenter implements IMySearchPresenter, IOnMySearchFinishedListener {

    private MySearchView        view;
    private MySearchInteractor  interactor;

    public MySearchPresenter(Context context, MySearchView view, Network network, User user) {
        this.view = view;
        interactor = new MySearchInteractor(context, network, user);
    }

    @Override
    public void getQueryTextSubmit(String query) {
//        view.showProgress();
    }

    @Override
    public void getQueryTextChange(String query) {
        view.showProgress();
        interactor.getQueryTextChange(query, view.getProgressBar(), this);
    }

    @Override
    public void addFriend(int volunteerId, String name) {
        view.showProgress();
        interactor.addFriend(volunteerId, name, view.getProgressBar(), this);
    }

    @Override
    public boolean isUser(int volunteerId) {
        return (volunteerId == interactor.getUserId());
    }

    @Override
    public void onDialog(String title, String msg) {
        view.hideProgress();
        view.setDialog(title, msg);
    }

    @Override
    public void onSuccessQueryTextSubmit(List<Volunteer> volunteers) {
        // Set ProgressBar Visibility
        view.hideProgress();
    }

    @Override
    public void onSuccessQueryTextChange(List<Volunteer> volunteers) {
        // Set RecyclerView
        view.getRvAdapter().update(volunteers);

        // Set ProgressBar Visibility
        view.hideProgress();
    }

    @Override
    public void onSuccessAddFriend(String name) {
        // Set ProgressBar Visibility
        view.hideProgress();

        System.out.println("SUCCESS");

        // Display Dialog Success Message
        new AlertDialog.Builder(view.getActivity())
                .setCancelable(true)
                .setTitle("Invitation envoyée")
                .setMessage("En attente de la réponse de " + name)
                .show();
    }
}
