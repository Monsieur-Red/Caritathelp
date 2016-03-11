package com.eip.red.caritathelp.Views.Organisation;

import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.eip.red.caritathelp.MainActivity.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation;
import com.eip.red.caritathelp.Presenters.Organisation.OrganisationPresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Organisation.OrganisationManagement.OrganisationManagementView;
import com.eip.red.caritathelp.Views.Organisation.OrganisationMembers.OrganisationMembersView;

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

        // Init ToolBar text
        EditText    editText = (EditText) view.findViewById(R.id.top_bar_organisation_search_text);
        editText.setText(organisation);

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
        setListViewHeightBasedOnChildren(listView);
        initListener();

        // Init Listener
        view.findViewById(R.id.top_bar_organisation_return).setOnClickListener(this);
        view.findViewById(R.id.top_bar_organisation_management).setOnClickListener(this);
        view.findViewById(R.id.organisation_btn_members).setOnClickListener(this);

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

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, WindowManager.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
