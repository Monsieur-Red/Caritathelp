package com.eip.red.caritathelp.Presenters.SubMenu.AccountSettings;

import android.widget.EditText;

import java.util.HashMap;

/**
 * Created by pierr on 22/01/2016.
 */
public interface IAccountSettingsPresenter {

    void saveModification(HashMap<Integer, EditText> modification);

}
