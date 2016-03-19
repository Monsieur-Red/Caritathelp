package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations.OrganisationCreation;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Organisation.OrganisationView;
import com.eip.red.caritathelp.Views.SubMenu.MyOrganisations.OrganisationCreation.OrganisationCreationView;

/**
 * Created by pierr on 24/02/2016.
 */

public class OrganisationCreationPresenter implements IOrganisationCreationPresenter, IOnOrganisationCreationsFinishedListener {

    private OrganisationCreationView        view;
    private OrganisationCreationInteractor  interactor;

    public OrganisationCreationPresenter(OrganisationCreationView view, Network network) {
        this.view = view;
        interactor = new OrganisationCreationInteractor(view.getContext(), network);
    }

    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.top_bar_organisation_creation_return:
                ((MainActivity) view.getActivity()).goToPreviousPage();
                break;
            case R.id.organisation_creation_btn_create:
                String  name = view.getName().getText().toString();
                String  theme = view.getTheme().getText().toString();
                String  city = view.getCity().getText().toString();
                String  description = view.getDescription().getText().toString();

                view.showProgress();
                interactor.createOrganisation(name, theme, city, description, this);
                break;
        }

    }

    @Override
    public void onNameError(String error) {
        view.hideProgress();
        view.setNameError(error);
    }

    @Override
    public void onThemeError(String error) {
        view.hideProgress();
        view.setThemeError(error);
    }

    @Override
    public void onCityError(String error) {
        view.hideProgress();
        view.setCityError(error);
    }

    @Override
    public void onDescriptionError(String error) {
        view.hideProgress();
        view.setDescriptionError(error);
    }

    @Override
    public void onDialogError(String title, String msg) {
        view.hideProgress();
        view.setDialogError(title, msg);
    }

    @Override
    public void onSuccess(Organisation organisation) {
        view.hideProgress();
        view.navigateToOrganisationView(organisation.getName());
        ((MainActivity) view.getActivity()).replaceView(OrganisationView.newInstance(organisation), Animation.FADE_IN_OUT);
    }
}
