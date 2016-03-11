package com.eip.red.caritathelp.Views.Organisation.OrganisationManagement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eip.red.caritathelp.MainActivity.MainActivity;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 24/02/2016.
 */
public class OrganisationManagementView extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init View
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_organisation_management, container, false);

        // Init Listener
        view.findViewById(R.id.top_bar_organisation_management_return).setOnClickListener(this);

        return (view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_organisation_management_return:
                ((MainActivity) getActivity()).goToPreviousPage();
                break;
        }
    }

}
