package com.eip.red.caritathelp.Presenters.SubMenu.Profile;

import android.content.Intent;
import android.widget.ImageView;

/**
 * Created by pierr on 11/05/2016.
 */

public interface IProfilePresenter {

    void onClick(int viewId);

    void getProfile(ImageView imageView);

    void getNews();

    void uploadProfileImg(ImageView imageView, Intent data);

}
