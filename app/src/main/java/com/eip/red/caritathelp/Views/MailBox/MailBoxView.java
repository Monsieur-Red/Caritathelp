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

    public static Fragment newInstance() {
        MailBoxView     fragment = new MailBoxView();
        Bundle          args = new Bundle();

        args.putInt("page", R.string.view_name_mailbox);
        fragment.setArguments(args);

        return (fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (inflater.inflate(R.layout.fragment_mailbox, container, false));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init ToolBar Title
        getActivity().setTitle(getArguments().getInt("page"));

        // Init NewsList Model
//        presenter.getNewsList();
    }

    @Override
    public void onClick(View v) {

    }
}
