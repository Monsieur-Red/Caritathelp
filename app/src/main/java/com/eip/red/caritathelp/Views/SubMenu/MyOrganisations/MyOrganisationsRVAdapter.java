package com.eip.red.caritathelp.Views.SubMenu.MyOrganisations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations.MyOrganisationsPresenter;
import com.eip.red.caritathelp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 28/03/2016.
 */

public class MyOrganisationsRVAdapter extends RecyclerView.Adapter<MyOrganisationsRVAdapter.DataObjectHolder> {

    private MyOrganisationsPresenter    presenter;

    private List<Organisation>  visibleObjects;
    private List<Organisation>  allObjects;

    public MyOrganisationsRVAdapter(MyOrganisationsPresenter presenter) {
        this.presenter = presenter;
        visibleObjects = new ArrayList<>();
        allObjects = new ArrayList<>();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.my_organisations_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Organisation    organisation = visibleObjects.get(getAdapterPosition());

            if (organisation != null)
                presenter.goToOrganisationView(organisation);
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_submenu_my_organisations_rv_row, parent, false);
        DataObjectHolder    holder = new DataObjectHolder(view);

        return (holder);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.name.setText(visibleObjects.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return (visibleObjects.size());
    }

    public void update(List<Organisation>  organisations) {
        visibleObjects.clear();
        allObjects.clear();

        visibleObjects.addAll(organisations);
        allObjects.addAll(organisations);

        notifyDataSetChanged();
    }

    public void flushFilter(){
        visibleObjects.clear();
        visibleObjects.addAll(allObjects);
        notifyDataSetChanged();
    }

    public void filter(String queryText) {
        visibleObjects.clear();

        for (Organisation organisation :  allObjects) {
            if (organisation.getName().toLowerCase(Locale.getDefault()).contains(queryText))
                visibleObjects.add(organisation);
        }
        notifyDataSetChanged();
    }
}
