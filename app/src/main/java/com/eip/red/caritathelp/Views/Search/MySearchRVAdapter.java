package com.eip.red.caritathelp.Views.Search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Search.Volunteer;
import com.eip.red.caritathelp.Presenters.Search.MySearchPresenter;
import com.eip.red.caritathelp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 19/04/2016.
 */

public class MySearchRVAdapter extends RecyclerView.Adapter<MySearchRVAdapter.DataObjectHolder> {

    private MySearchPresenter   presenter;
    private List<Volunteer>     searchDataList;

    public MySearchRVAdapter(MySearchPresenter presenter) {
        this.presenter = presenter;
        searchDataList = new ArrayList<>();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView   image;
        TextView    name;
        ImageButton add;

        public DataObjectHolder(View itemView) {
            super(itemView);

            // Init UI Element
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            add = (ImageButton) itemView.findViewById(R.id.btn_add_friend);

            // Init Listener
            itemView.setOnClickListener(this);
            image.setOnClickListener(this);
            name.setOnClickListener(this);
            add.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image:
                    // Redirect Volunteer Profile Page
                    break;
                case R.id.name:
                    // Redirect Volunteer Profile Page
                    break;
                case R.id.btn_add_friend:
                    // Add Friend Request
                    Volunteer   volunteer = searchDataList.get(getAdapterPosition());
                    String      name = volunteer.getFirstname() + " " + volunteer.getLastname();

                    presenter.addFriend(volunteer.getId(), name);
                    break;
            }
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_search_rv_row, parent, false);
        DataObjectHolder    holder = new DataObjectHolder(view);

        return (holder);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        Volunteer   volunteer = searchDataList.get(position);
        String      name = volunteer.getFirstname() + " " + volunteer.getLastname();

        holder.name.setText(name);

        if (presenter.isUser(volunteer.getId()))
            holder.add.setVisibility(View.INVISIBLE);
        else
            holder.add.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return (searchDataList.size());
    }

    public void update(List<Volunteer> volunteers) {
        searchDataList.clear();

        if (volunteers != null)
            searchDataList.addAll(volunteers);

        notifyDataSetChanged();
    }
}
