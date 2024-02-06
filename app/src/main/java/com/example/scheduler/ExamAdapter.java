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


public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {
    private List<Exam> examList;
    private Context context;
    private TextView Date, time, loc;
    private int clickedPosition = -1;
    private Date date;

    public ExamAdapter(List<Exam> examList) {

        this.examList = examList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exam newExam = examList.get(position);

        holder.courseTextView.setText(newExam.getDate());
        holder.instructorTextView.setText(newExam.getTime());
        holder.timeTextView.setText(newExam.getLocation());
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
        return examList.size();
    }

    public void addAssign(String date, String time,
                          String loc) {
        Exam newExam = new Exam(date,time, loc);
        examList.add(newExam);
    }
    public void editExam(String date, String time,
                           String loc){

        examList.get(clickedPosition).setDate(date);
        examList.get(clickedPosition).setTime(time);
        examList.get(clickedPosition).setLocation(loc);
    }
    public void deleteExam(){
        examList.remove(clickedPosition);
    }
    public int getClickedPosition(){
        return clickedPosition;
    }
    //Comparing the dates
    public void sortAssignmentsByDueDate() {
        Collections.sort(examList, new Comparator<Exam>() {
            @Override
            public int compare(Exam exam1, Exam exam2) {
                // Compare due dates using the compareTo method in the Date class
                return exam1.getFormattedDate().compareTo(exam2.getFormattedDate());
            }
        });
        notifyDataSetChanged(); // Notify the adapter after sorting

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
