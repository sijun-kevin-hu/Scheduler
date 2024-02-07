package com.example.scheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssignmentFragment extends Fragment {
    private RecyclerView recyclerView;
    private AssignAdapter adapter;
    private FloatingActionButton addButton;
    private TextInputEditText assignTitleInput, assignCourseInput, dueDateInput;
    private LayoutInflater inflater;
    private NavigationView navigationView;
    public AssignViewModel assignViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize the ViewModel
        assignViewModel = new ViewModelProvider(this).get(AssignViewModel.class);
        //initialize the adapter with the ViewModel
        adapter = new AssignAdapter(assignViewModel.getAssignLiveData().getValue());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_assignment, container, false);
        recyclerView = root.findViewById(R.id.assignContainer);
        addButton = root.findViewById(R.id.addAssignButton);
        this.inflater = inflater;

        // Initialize RecyclerView layout manager and adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //registering the context menu
        registerForContextMenu(recyclerView);

        //Setting the adapter
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
        View dialogView = inflater.inflate(R.layout.assignlayout, null);

        // Set up the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView);
        builder.setTitle("Add Course");

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the course when the "Add" button in the dialog is clicked
                assignTitleInput = dialogView.findViewById(R.id.assignTitleInput);
                assignCourseInput = dialogView.findViewById(R.id.assignCourseInput);
                dueDateInput = dialogView.findViewById(R.id.assignDueDateInput);

                String assignTitle = assignTitleInput.getText().toString().trim();
                String assignCourse = assignCourseInput.getText().toString().trim();
                String dueDate = dueDateInput.getText().toString().trim();
                if (!assignTitle.isEmpty() && !assignCourse.isEmpty()
                        && !dueDate.isEmpty()) {
                    adapter.addAssign(assignTitle, assignCourse, dueDate);
                    //Sort it based on Date
                    adapter.sortAssignmentsByDueDate();
                    //Set the Adapter
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Assignments fields can not be empty", Toast.LENGTH_SHORT).show();
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
        View dialog = inflater.inflate(R.layout.assignlayout, null);
        //setUp the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialog);
        builder.setTitle("Edit the Course");
        assignTitleInput = dialog.findViewById(R.id.assignTitleInput);
        assignCourseInput = dialog.findViewById(R.id.assignCourseInput);
        dueDateInput = dialog.findViewById(R.id.assignDueDateInput);

        //Action buttons
        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.editAssign(assignTitleInput.getText().toString().trim(),
                        assignCourseInput.getText().toString().trim(),
                        dueDateInput.getText().toString().trim());
                //Sort it based on Date
                adapter.sortAssignmentsByDueDate();
                recyclerView.setAdapter(adapter);
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
        builder.setTitle("Delete Course");
        builder.setMessage("Are you sure you want to delete this assignment?");
        //Action buttons
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //delete the course
                adapter.deleteAssign();
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

}