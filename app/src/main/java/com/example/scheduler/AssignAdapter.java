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


public class AssignAdapter extends RecyclerView.Adapter<AssignAdapter.ViewHolder> {
    private List<Assignment> assignList;
    private Context context;
    private TextView title, name, time;
    private int clickedPosition = -1;
    private Date date;

    public AssignAdapter(List<Assignment> assignList) {

        this.assignList = assignList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Assignment newAssign = assignList.get(position);

        holder.courseTextView.setText(newAssign.getName());
        holder.instructorTextView.setText(newAssign.getCourseName());
        holder.timeTextView.setText(newAssign.getDueDate());
        //getting clicked position


        // Set up context menu for each item
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                context = v.getContext();
                clickedPosition = position;
                return false;
            }
        });

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                // menu.setHeaderTitle("Options");
                menu.add(Menu.NONE, R.id.edit_option, Menu.NONE, "Edit");
                menu.add(Menu.NONE, R.id.delete_option, Menu.NONE, "Delete");
                menu.add(Menu.NONE, R.id.sortby_course, Menu.NONE, "sort by Course");
                menu.add(Menu.NONE, R.id.sortby_date, Menu.NONE, "Sort by Date");

            }
        });
    }


    @Override
    public int getItemCount() {
        return assignList.size();
    }

    public void addAssign(String assignTitle, String assignCourse,
                          String dueDate) {
        Assignment newAssign = new Assignment(assignTitle, assignCourse, dueDate);
        assignList.add(newAssign);
    }
    public void editAssign(String assignTitle, String assignCourse,
                           String dueDate){

        assignList.get(clickedPosition).setName(assignTitle);
        assignList.get(clickedPosition).setCourseName(assignCourse);
        assignList.get(clickedPosition).setDueDate(dueDate);
    }
    public void deleteAssign(){
        assignList.remove(clickedPosition);
    }
    public int getClickedPosition(){
        return clickedPosition;
    }
    //Comparing the dates
    public void sortAssignmentsByDueDate() {
        Collections.sort(assignList, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment assign1, Assignment assign2) {
                // Compare due dates using the compareTo method in the Date class
                return assign1.getFormattedDate().compareTo(assign2.getFormattedDate());
            }
        });
        notifyDataSetChanged(); // Notify the adapter after sorting

    }

    public  void sortByCourse() {
       Collections.sort(assignList, new Comparator<Assignment>() {
           @Override
           public int compare(Assignment o1, Assignment o2) {
               return o1.getCourseName().compareTo(o2.getCourseName());
           }
       });

       notifyDataSetChanged();
    }

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
            menu.add(Menu.NONE, R.id.sortby_course, Menu.NONE, "sort by Course");
            menu.add(Menu.NONE, R.id.sortby_date, Menu.NONE, "Sort by Date");

        }
    }

}
