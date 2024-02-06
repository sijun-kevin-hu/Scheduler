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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Calendar;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<ToDoList> toDoList;
    private Context context;
    private TextView title, name, time;
    private int clickedPosition = -1;
    private Date date;
    private NotifAdapter notif;

    public ToDoAdapter(List<ToDoList> toDoList) {

        this.toDoList = toDoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoList newToDo = toDoList.get(position);

        holder.courseTextView.setText(newToDo.getDate());
        holder.instructorTextView.setText(newToDo.getType());
        holder.timeTextView.setText(newToDo.getDiscr());
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
        return toDoList.size();
    }

    public void addToDo(String toDoDate, String toDoType,
                          String toDoDiscr) {
        ToDoList newTodo = new ToDoList(toDoDate, toDoType,toDoDiscr);
        toDoList.add(newTodo);
        Calendar toDay = Calendar.getInstance();
        //add TodoList into Notification List
        if (toDay.equals(newTodo.getFormattedDate())) {
            notif.addNotif("Todo List", toDoDiscr, toDoDate);
        }
    }
    public void editToDo(String toDoDate, String toDoType,
                           String toDoDiscr){

        toDoList.get(clickedPosition).setDate(toDoDate);
        toDoList.get(clickedPosition).setType(toDoType );
        toDoList.get(clickedPosition).setDiscr(toDoDiscr);
    }
    public void deleteToDo(){
        toDoList.remove(clickedPosition);
    }
    public int getClickedPosition(){
        return clickedPosition;
    }
    //Comparing the dates
    public void sortAssignmentsByDueDate() {
        Collections.sort(toDoList, new Comparator<ToDoList>() {
            @Override
            public int compare(ToDoList todo1, ToDoList todo2) {
                // Compare due dates using the compareTo method in the Date class
                return todo1.getFormattedDate().compareTo(todo2.getFormattedDate());
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


