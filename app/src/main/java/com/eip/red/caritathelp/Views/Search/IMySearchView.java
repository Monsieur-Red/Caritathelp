package com.eip.red.caritathelp.Views.Search;

import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Models.Search.Volunteer;

import java.util.List;

/**
 * Created by pierr on 21/04/2016.
 */

public interface IMySearchView {

    void show();

    void showProgress();

    void hideProgress();

    void setDialog(String title, String msg);
}
