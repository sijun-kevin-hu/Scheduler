package com.example.scheduler;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.ViewHolder> {
    private List<Notification> notifList;
    private Context context;
    private TextView title, name, time;


    public NotifAdapter(List<Notification> notifList) {

        this.notifList = notifList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification newNotif = notifList.get(position);

        holder.courseTextView.setText(newNotif.getType());
        holder.instructorTextView.setText(newNotif.getContent());
        holder.timeTextView.setText(newNotif.getDueDate());
        //getting clicked position
    }


    @Override
    public int getItemCount() {
        return notifList.size();
    }

    public void addNotif(String type, String content, String dudDate) {
        Notification newNotif = new Notification(type, content, dudDate);
        notifList.add(newNotif);
    }

    /**
    //Comparing the dates
    public void sortNotifsByDueDate() {
        Collections.sort(notifList, new Comparator<NotificationItem>() {
            @Override
            public int compare(NotificationItem item1, NotificationItem item2) {
                // Compare due dates using the compareTo method
                //return item1.getFormattedDate().compareTo(item2.getFormattedDate());
            }
        });
        notifyDataSetChanged();
    }
     */

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView courseTextView, instructorTextView, timeTextView;

        //modify here
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTextView = itemView.findViewById(R.id.courseTitleTextView);
            instructorTextView = itemView.findViewById(R.id.instructorNameTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Options");
            menu.add(Menu.NONE, R.id.edit_option, Menu.NONE, "Edit");
            menu.add(Menu.NONE, R.id.delete_option, Menu.NONE, "Delete");
        }
    }
}