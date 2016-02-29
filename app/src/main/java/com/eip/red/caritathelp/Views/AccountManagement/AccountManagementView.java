package com.eip.red.caritathelp.Views.AccountManagement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eip.red.caritathelp.MainActivity.MainActivity;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 11/01/2016.
 */

public class AccountManagementView extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        newsList = ((MainActivity) getActivity()).getModelManager().getNewsList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return (view);
    }

}
