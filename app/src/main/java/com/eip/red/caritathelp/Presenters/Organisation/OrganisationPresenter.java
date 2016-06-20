package com.eip.red.caritathelp.Presenters.Organisation;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Home.News;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;
import com.eip.red.caritathelp.Views.Organisation.Events.OrganisationEventsView;
import com.eip.red.caritathelp.Views.Organisation.Informations.OrganisationInformationsView;
import com.eip.red.caritathelp.Views.Organisation.Management.OrganisationManagementView;
import com.eip.red.caritathelp.Views.Organisation.Members.OrganisationMembersView;
import com.eip.red.caritathelp.Views.Organisation.OrganisationView;

import java.util.List;

/**
 * Created by pierr on 11/03/2016.
 */

public class OrganisationPresenter implements IOrganisationPresenter, IOnOrganisationFinishedListener {

    private OrganisationView        view;
    private OrganisationInteractor  interactor;

    public OrganisationPresenter(OrganisationView view, String token, int id) {
        this.view = view;

        // Init Interactor
        interactor = new OrganisationInteractor(view.getActivity().getApplicationContext(), token, id);
    }

    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.btn_management:
                Tools.replaceView(view, OrganisationManagementView.newInstance(interactor.getOrganisationId()), Animation.FADE_IN_OUT, false);
                break;
            case R.id.btn_join:
                break;
            case R.id.btn_follow:
                break;
            case R.id.btn_post:
                break;
            case R.id.btn_members:
                Tools.replaceView(view, OrganisationMembersView.newInstance(interactor.getOrganisationId()), Animation.FADE_IN_OUT, false);
                break;
            case R.id.btn_events:
                Tools.replaceView(view, OrganisationEventsView.newInstance(interactor.getOrganisationId()), Animation.FADE_IN_OUT, false);
                break;
            case R.id.btn_informations:
                Tools.replaceView(view, OrganisationInformationsView.newInstance(interactor.getOrganisationId()), Animation.FADE_IN_OUT, false);
                break;
        }

    }

    @Override
    public void getOrganisation() {
        view.showProgress();
        interactor.getOrganisation(this);
    }

    @Override
    public void getNews() {
        view.showProgress();
        interactor.getNews(this);
    }

    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
        view.setDialog(title, msg);
    }

    @Override
    public void onOrganisationRequestSuccess(String right) {
        // Set Progress Bar Visibility
        view.hideProgress();

        // Set Management Button Visibility
        view.initView(right);
    }

    @Override
    public void onNewsRequestSuccess(List<News> newsList) {
        view.hideProgress();
        view.updateRV(newsList);
    }
}
