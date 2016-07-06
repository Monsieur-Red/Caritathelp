package com.eip.red.caritathelp.Presenters.Organisation;

import com.eip.red.caritathelp.Models.Home.News;
import com.eip.red.caritathelp.Models.Organisation.Organisation;

import java.util.List;

/**
 * Created by pierr on 28/03/2016.
 */

public interface IOnOrganisationFinishedListener {

    void onDialogError(String title, String msg);

    void onOrganisationRequestSuccess(String thumb, String right);

    void onNewsRequestSuccess(List<News> newsList);
}
