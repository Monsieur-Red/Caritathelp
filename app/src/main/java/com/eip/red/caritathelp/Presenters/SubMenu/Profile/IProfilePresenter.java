package com.eip.red.caritathelp.Presenters.SubMenu.Profile;

import android.content.Intent;

import com.pkmmte.view.CircularImageView;

/**
 * Created by pierr on 11/05/2016.
 */

public interface IProfilePresenter {

    void initProfileImg(CircularImageView imageView);

    void uploadProfileImg(CircularImageView imageView, Intent data);

    void onClick(int viewId);

    void getProfile();

    void getNews();

}
