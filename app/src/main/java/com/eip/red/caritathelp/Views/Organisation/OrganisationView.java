package com.eip.red.caritathelp.Views.Organisation;

import android.app.Fragment;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Presenters.Organisation.OrganisationPresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;

/**
 * Created by pierr on 18/02/2016.
 */

public class OrganisationView extends Fragment implements View.OnClickListener {

    private OrganisationPresenter   presenter;

    private ListView    listView;

    public static OrganisationView newInstance(Organisation organisation) {
        OrganisationView    myFragment = new OrganisationView();

        Bundle args = new Bundle();

        args.putSerializable("organisation", organisation);
        myFragment.setArguments(args);

        return (myFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Network & Organisation Model
//        Network         network = ((SecondContainer) getActivity()).getModelManager().getNetwork();
        Network         network = ((MainActivity) getActivity()).getModelManager().getNetwork();
        Organisation    organisation = (Organisation) getArguments().getSerializable("organisation");

        // Init Presenter
        presenter = new OrganisationPresenter(this, network, organisation);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_organisation, container, false);

        // Get Organisation Name
        String organisation = presenter.getOrganisationName();

        // Set ToolBar
        ((MainActivity) getActivity()).getToolBar().update(organisation, true, false);

        // Init TextView Organisation Name
        TextView textView = (TextView) view.findViewById(R.id.organisation_name);
        textView.setText(organisation);

        // Init Image Filter (Darken the image)
        ImageView               imageView = (ImageView) view.findViewById(R.id.organisation_image);
        LightingColorFilter     lcf = new LightingColorFilter(0xFF888888, 0x00222222);
        imageView.setColorFilter(lcf);

        // Init ListView & Listener & Adapter
        listView = (ListView) view.findViewById(R.id.organisation_list_view);
        listView.setAdapter(new OrganisationListViewAdapter(this));
        Tools.setListViewHeightBasedOnChildren(listView);
        initListener();

        // Init Listener
        view.findViewById(R.id.organisation_btn_join).setOnClickListener(this);
        view.findViewById(R.id.organisation_btn_management).setOnClickListener(this);
        view.findViewById(R.id.organisation_btn_members).setOnClickListener(this);
        view.findViewById(R.id.organisation_btn_events).setOnClickListener(this);

        return (view);
    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        presenter.onClick(v.getId());
    }


}
