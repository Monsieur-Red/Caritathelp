package com.eip.red.caritathelp.Views.SubMenu.Friends;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Friends.Friend;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.Presenters.SubMenu.Friends.FriendsPresenter;
import com.eip.red.caritathelp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 23/04/2016.
 */

public class MyFriendsRVAdpater extends RecyclerView.Adapter<MyFriendsRVAdpater.DataObjectHolder> {

    private FriendsPresenter    presenter;
    private List<Friend>        friends;

    public MyFriendsRVAdpater(FriendsPresenter presenter) {
        this.presenter = presenter;
        friends = new ArrayList<>();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView   image;
        TextView    name;
        ImageButton block;
        ImageButton remove;

        public DataObjectHolder(View itemView) {
            super(itemView);

            // Init UI Elements
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            block = (ImageButton) itemView.findViewById(R.id.btn_block);
            remove = (ImageButton) itemView.findViewById(R.id.btn_remove);

            // Init Listeners
            image.setOnClickListener(this);
            name.setOnClickListener(this);
            block.setOnClickListener(this);
            remove.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Friend  friend = friends.get(getAdapterPosition());

            if (friend != null) {
                switch (v.getId()) {
                    case R.id.image:
                        presenter.navigateToFriendView(friend.getId());
                        break;
                    case R.id.name:
                        presenter.navigateToFriendView(friend.getId());
                        break;
                    case R.id.btn_block:
                        presenter.blockFriend(friend.getId());
                        break;
                    case R.id.btn_remove:
                        presenter.removeFriend(friend);
                        break;
                }
            }
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_submenu_friends_my_friends_rv_row, parent, false);
        DataObjectHolder    holder = new DataObjectHolder(view);

        return (holder);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        Friend  friend = friends.get(position);
        String name = friend.getFirstname() + " " + friend.getLastname();

        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return (friends.size());
    }

    public void update(List<Friend> newFriends) {
        friends.clear();
        friends.addAll(newFriends);
        notifyDataSetChanged();
    }

}
