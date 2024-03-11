package com.example.scheduler;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodoListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoListFragment extends Fragment {
    private RecyclerView recyclerView;
    private ToDoAdapter adapter;
    private FloatingActionButton addButton;
    private TextInputEditText toDodateInput, toDoTypeInput, toDODiscrInput;
    private LayoutInflater inflater;
    private NavigationView navigationView;
    public TodoViewModel todoViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        adapter = new ToDoAdapter(todoViewModel.getListMutableLiveData().getValue());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_todo_list, container, false);
        recyclerView = root.findViewById(R.id.toDoContainer);
        addButton = root.findViewById(R.id.addToDoButton);
        this.inflater = inflater;

        // Initialize RecyclerView layout manager and adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //registering the context menu
        registerForContextMenu(recyclerView);

       //Initialize the recyclerView with adapter
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add new course item to the list
                showAddCourseDialog();
            }
        });
        return root;
    }

    private void showAddCourseDialog() {
        // Inflate the dialog layout
        View dialogView = inflater.inflate(R.layout.todolayout, null);

        // Set up the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView);
        builder.setTitle("Add TodoList");
        //set up date picker
        datePicker(dialogView);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the course when the "Add" button in the dialog is clicked
                toDoTypeInput = dialogView.findViewById(R.id.toDoTypeInput);
                toDODiscrInput = dialogView.findViewById(R.id.toDoDiscrInput);

                String toDoDate= toDodateInput.getText().toString().trim();
                String toDoType = toDoTypeInput.getText().toString().trim();
                String toDoDiscr = toDODiscrInput.getText().toString().trim();

                if (!toDoType.isEmpty() && !toDoDate.isEmpty()
                        && !toDoDiscr.isEmpty()) {
                    adapter.addToDo(toDoDate, toDoType, toDoDiscr);
                    //Sort it based on Date
                    adapter.sortAssignmentsByDueDate();
                    //Set the Adapter
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Todo List fields can not be empty", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Dismiss the dialog when the "Cancel" button is clicked
            }
        });

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showEditCourseDialog(int position) {
        //Inflate the dialog
        View dialog = inflater.inflate(R.layout.todolayout, null);
        //setUp the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialog);
        builder.setTitle("Edit the Todo List");

        //set Up date picker
        datePicker(dialog);

        toDoTypeInput= dialog.findViewById(R.id.toDoDateInput);
        toDODiscrInput = dialog.findViewById(R.id.toDoDiscrInput);

        //Action buttons
        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String toDoDate = toDodateInput.getText().toString().trim();
                adapter.editToDo(toDodateInput.getText().toString().trim(),
                        toDoTypeInput.getText().toString().trim(),
                        toDODiscrInput.getText().toString().trim());
                //Sort it based on Date
                adapter.sortAssignmentsByDueDate();
                recyclerView.setAdapter(adapter);
                Toast.makeText(getContext(), "Todo List is successfully updated!!", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDeleteCourseDialog(int position) {
        //Creat new Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Delete Todo List");
        builder.setMessage("Are you sure you want to delete this Todo List?");
        //Action buttons
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //delete the course
                adapter.deleteToDo();
                adapter.notifyItemRemoved(position);
                //  recyclerView.setAdapter(adapter);
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getClickedPosition();
        if (item.getItemId() == R.id.edit_option) {
            // Show edit dialog or navigate to edit screen
            showEditCourseDialog(position);
            return true;
        } else if (item.getItemId() == R.id.delete_option) {
            // Remove the selected course from the list and notify adapter
            showDeleteCourseDialog(position);
            return true;
        }
        return super.onContextItemSelected(item);
    }
    // Date picker void method
    void datePicker(View dialogView) {
        //Set date picker
        toDodateInput = dialogView.findViewById(R.id.toDoDateInput);
        toDodateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int year = cldr.get(Calendar.YEAR);
                int months = cldr.get(Calendar.MONTH);
                int dayOfMonth = cldr.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                toDodateInput.setText((String.format("%02d", month+1) +"/"+ String.format("%02d",
                                        dayOfMonth) +"/"+year%100));
                            }
                        }, year, months, dayOfMonth);
                datePickerDialog.show();
            }
        });
    }
}