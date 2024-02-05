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

import com.example.scheduler.Course;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    private List<Course> coursesList;
    private Context context;
    private TextView title, name, time;
    private int clickedPosition = -1;

    public CoursesAdapter(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course courseTile = coursesList.get(position);

        holder.courseTextView.setText(courseTile.getName());
        holder.instructorTextView.setText(courseTile.getInstructor());
        holder.timeTextView.setText(courseTile.getName());
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
            }
        });
    }


    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public void addCourse(String courseName, String instructorName,
                          String time) {
        Course newCourse = new Course(courseName, instructorName);
        coursesList.add(newCourse);
    }
    public void editCourse(String courseName, String instructorName,
                           String time){
        coursesList.get(clickedPosition).setName(courseName);
        coursesList.get(clickedPosition).setInstructor(instructorName);
       // coursesList.get(clickedPosition).setTime(time);
    }
    public void deleteCourse(){
        coursesList.remove(clickedPosition);
    }
    public int getClickedPosition(){
        return clickedPosition;
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
        }
    }
}