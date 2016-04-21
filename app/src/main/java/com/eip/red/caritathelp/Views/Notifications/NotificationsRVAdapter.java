package com.eip.red.caritathelp.Views.Notifications;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Notifications.Notification;
import com.eip.red.caritathelp.Presenters.Notifications.NotificationsPresenter;
import com.eip.red.caritathelp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 21/04/2016.
 */

public class NotificationsRVAdapter extends RecyclerView.Adapter<NotificationsRVAdapter.DataObjectHolder> {

    private NotificationsPresenter  presenter;
    private List<Notification>      notifications;

    public NotificationsRVAdapter(NotificationsPresenter presenter) {
        this.presenter = presenter;
        notifications = new ArrayList<>();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView   image;
        TextView    message;
        TextView    date;

        public DataObjectHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            message = (TextView) itemView.findViewById(R.id.message);
            date = (TextView) itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Notification    notification = notifications.get(getAdapterPosition());

            if (notification != null)
                presenter.friendshipReply(notification);
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_notifications_rv_row, parent, false);
        DataObjectHolder    holder = new DataObjectHolder(view);

        return (holder);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        Notification    notification = notifications.get(position);
        String          msg = notification.getFirstname() + " " + notification.getLastname() + " demande à rejoindre votre liste d'amis.";

        holder.message.setText(msg);
    }

    @Override
    public int getItemCount() {
        return (notifications.size());
    }

    public void update(List<Notification> newNotifications) {
        notifications.clear();
        notifications.addAll(newNotifications);
        notifyDataSetChanged();
    }

}
