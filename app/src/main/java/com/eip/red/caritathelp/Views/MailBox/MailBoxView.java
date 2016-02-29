package com.eip.red.caritathelp.Views.MailBox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 19/01/2016.
 */

public class MailBoxView extends Fragment implements View.OnClickListener {

    private View    view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mailbox, container, false);

        return (view);
    }


    @Override
    public void onClick(View v) {

    }
}
