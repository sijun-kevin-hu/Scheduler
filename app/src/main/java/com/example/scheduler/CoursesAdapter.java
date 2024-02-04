package com.example.scheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    private List<String> coursesList;

    public CoursesAdapter(List<String> coursesList) {
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
        String course = coursesList.get(position);
       holder.courseTextView.setText("Hello");
       holder.instructorTextView.setText("Me");
       holder.timeTextView.setText("1:00");
    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public void addCourse(String courseName) {
        coursesList.add(courseName);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseTextView, instructorTextView, timeTextView;
//modify here
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTextView = itemView.findViewById(R.id.courseTitleTextView);
            instructorTextView = itemView.findViewById(R.id.instructorNameTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);

        }
    }
}
