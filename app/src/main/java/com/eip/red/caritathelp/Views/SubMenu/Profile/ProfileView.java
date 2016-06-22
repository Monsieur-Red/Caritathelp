package com.eip.red.caritathelp.Views.SubMenu.Profile;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.User.User;
import com.eip.red.caritathelp.Presenters.SubMenu.Profile.ProfilePresenter;
import com.eip.red.caritathelp.R;
import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * Created by pierr on 11/05/2016.
 */

public class ProfileView extends Fragment implements IProfileView, View.OnClickListener {

    private ProfilePresenter presenter;

    private CircularImageView   profileImg;
    private TextView            name;
    private ImageButton         addFriend;
    private ProgressBar         progressBar;
    private AlertDialog         dialog;

    public static ProfileView newInstance(int id) {
        ProfileView    myFragment = new ProfileView();

        Bundle args = new Bundle();

        args.putInt("page", R.string.view_name_submenu_profile);
        args.putInt("id", id);
        myFragment.setArguments(args);

        return (myFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get User Model
        User    user = ((MainActivity) getActivity()).getModelManager().getUser();
        int     id = getArguments().getInt("id");

        // Init Presenter
        presenter = new ProfilePresenter(this, user, id);

        // Init Dialog
        dialog = new AlertDialog.Builder(getContext())
                .setCancelable(true)
                .create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submenu_profile, container, false);

        // Init UI Element
        profileImg = (CircularImageView) view.findViewById(R.id.image);
        name = (TextView) view.findViewById(R.id.name);
        addFriend = (ImageButton) view.findViewById(R.id.btn_add_friend);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Init Listener
        profileImg.setOnClickListener(this);
        view.findViewById(R.id.btn_add_friend).setOnClickListener(this);
        view.findViewById(R.id.btn_send_message).setOnClickListener(this);
        view.findViewById(R.id.btn_friends).setOnClickListener(this);
        view.findViewById(R.id.btn_organisations).setOnClickListener(this);
        view.findViewById(R.id.btn_events).setOnClickListener(this);

        return (view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init ToolBar Title
        getActivity().setTitle(getArguments().getInt("page"));

        // Init Profile Image
        presenter.initProfileImg(profileImg);

        // Get profile Model
        presenter.getProfile();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // When an Image is picked
        if (requestCode == ProfilePresenter.RESULT_LOAD_IMAGE && resultCode == MainActivity.RESULT_OK && data != null)
            presenter.uploadProfileImg(profileImg, data);
        else if (requestCode == ProfilePresenter.RESULT_CAPTURE_IMAGE && resultCode == MainActivity.RESULT_OK && data != null)
            presenter.uploadProfileImg(profileImg, data);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDialog(String title, String msg) {
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        presenter.onClick(v.getId());
    }

    public CircularImageView getProfileImg() {
        return profileImg;
    }

    public TextView getName() {
        return name;
    }

    public ImageButton getAddFriend() {
        return addFriend;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
