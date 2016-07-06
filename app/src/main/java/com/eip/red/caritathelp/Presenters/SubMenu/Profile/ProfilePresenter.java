package com.eip.red.caritathelp.Presenters.SubMenu.Profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Friends.Friend;
import com.eip.red.caritathelp.Models.User.User;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;
import com.eip.red.caritathelp.Views.SubMenu.Friends.FriendsView;
import com.eip.red.caritathelp.Views.SubMenu.MyEvents.MyEventsView;
import com.eip.red.caritathelp.Views.SubMenu.MyOrganisations.MyOrganisationsView;
import com.eip.red.caritathelp.Views.SubMenu.Profile.ProfileView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by pierr on 11/05/2016.
 */

public class ProfilePresenter implements IProfilePresenter, IOnProfileFinishedListener {

    static final public  int     RESULT_LOAD_IMAGE = 1;
    static final public  int     RESULT_CAPTURE_IMAGE = 2;

    private ProfileView         view;
    private ProfileInteractor   interactor;

    private AlertDialog         dialog;

    public ProfilePresenter(final ProfileView view, User user, int id) {
        this.view = view;
        interactor = new ProfileInteractor(view.getContext(), user, id);

        // Init Dialog
        CharSequence[] items = {"Prendre une photo", "Accéder à mes photos"};
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext())
                .setCancelable(true)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            // Create Intent to take a picture and return control to the calling application
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            try {
                                File    photoFile = Tools.createImageFile();
//                                Uri     photoURI = FileProvider.getUriForFile(view.getContext(), "com.example.android.fileprovider", photoFile);
//                                File    photoFile = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");
                                Uri photoURI = Uri.fromFile(photoFile);

                                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                view.startActivityForResult(intent, RESULT_CAPTURE_IMAGE);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

/*
                            // Get the Image
                            File imagesFolder = new File(Environment.getExternalStorageDirectory(), "Caritathelp");
                            imagesFolder.mkdirs();
                            File image = new File(imagesFolder, "test.jpg");
                            Uri uriSavedImg = Uri.fromFile(image);


                            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImg);

                            // Start the image capture Intent
                            view.startActivityForResult(intent, RESULT_CAPTURE_IMAGE);
*/
                        }
                        else {
                            // Create intent to Open Image applications like Gallery, Google Photos
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            // Start the Intent
                            view.startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                        }
                    }
                });
        dialog = builder.create();
    }

    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.image:
                // Check if user == main user
                // If true -> modify his profile img
                if (interactor.isMainUser())
                    dialog.show();
                break;
            case R.id.btn_add_friend:
                view.showProgress();
                interactor.addFriend(this, view.getProgressBar(), view.getName().toString());
                break;
            case R.id.btn_send_message:
                break;
            case R.id.btn_friends:
                Tools.replaceView(view, FriendsView.newInstance(interactor.getUserId()), Animation.FADE_IN_OUT, false);
                break;
            case R.id.btn_organisations:
                Tools.replaceView(view, MyOrganisationsView.newInstance(interactor.getUserId(), interactor.isMainUser()), Animation.FADE_IN_OUT, false);
                break;
            case R.id.btn_events:
                Tools.replaceView(view, MyEventsView.newInstance(interactor.getUserId(), interactor.isMainUser()), Animation.FADE_IN_OUT, false);
                break;
        }
    }

    @Override
    public void getProfile(ImageView imageView) {
        view.showProgress();
        interactor.getProfile(this, imageView, view.getProgressBar());
    }

    @Override
    public void getNews() {
        view.showProgress();
    }

    @Override
    public void uploadProfileImg(ImageView imageView, Intent data) {
        view.showProgress();

        // Set the image
        // Get the Image from data
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        // Get the cursor
        Cursor cursor = view.getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        String picturePath = null;

        // Move to first row
        if (cursor != null) {
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
        }

        // Set the Image in ImageView after decoding the String
        Bitmap bm = BitmapFactory.decodeFile(picturePath);
        view.getProfileImg().setImageBitmap(bm);

        // Upload the image on the server
        // Encoded the bitmap in base64
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArrayImage = baos.toByteArray();
        String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

        interactor.uploadProfileImg(encodedImage, "filename.jpg", "original_filename.jpg", -1, -1, "true", this);
    }

    @Override
    public void onDialog(String title, String msg) {
        view.hideProgress();
        view.setDialog(title, msg);
    }

    @Override
    public void onSuccessUploadProfileImg() {
        view.hideProgress();

        // Dialog Msg
        new AlertDialog.Builder(view.getContext())
                .setMessage("Votre image de profil a bien été modifié.")
                .setCancelable(true)
                .show();
    }

    @Override
    public void onSuccessGetProfile(User user, List<Friend> friends) {
        // Set User Profile Name
        String name = user.getFirstname() + " " + user.getLastname();
        view.getName().setText(name);

        // Set Add Friend Button Visibility
        if (friends != null && !isFriend(friends, user.getId()))
            view.getAddFriend().setVisibility(View.VISIBLE);

        // Set ProgressBar Visibility
        view.hideProgress();
    }

    private boolean isFriend(List<Friend> friends, int userId) {
        for (Friend friend : friends) {
            if (friend.getId() == userId)
                return (true);
        }
        return (false);
    }

    @Override
    public void onSuccessGetNews() {

    }

    @Override
    public void onSuccessAddFriend(String name) {
        // Set Create Btn Visibility
        view.getAddFriend().setVisibility(View.INVISIBLE);

        // Set ProgressBar Visibility
        view.hideProgress();

        // Display Dialog Success Message
        new AlertDialog.Builder(view.getActivity())
                .setCancelable(true)
                .setTitle("Invitation envoyée")
                .setMessage("En attente de la réponse de " + name)
                .show();
    }
}
