package com.eip.red.caritathelp.Views.SubMenu.Friends;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.eip.red.caritathelp.Presenters.SubMenu.Friends.FriendsPresenter;

/**
 * Created by pierr on 01/07/2016.
 */

public class SentRVAdapter extends RecyclerView.Adapter<SentRVAdapter.DataObjectHolder> {

    private FriendsPresenter presenter;

    public SentRVAdapter(FriendsPresenter presenter) {
        this.presenter = presenter;
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public DataObjectHolder(View itemView) {
            super(itemView);

            // Init UI Elements

            // Init Listeners

        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
