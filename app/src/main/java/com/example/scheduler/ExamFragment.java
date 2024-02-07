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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExamFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExamAdapter adapter;
    private FloatingActionButton addButton;
    private TextInputEditText examDateInput, examTimeInput, examLocationInput;
    private LayoutInflater inflater;
    private NavigationView navigationView;
    public ExamViewModel examViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);
        adapter = new ExamAdapter(examViewModel.getExamLiveData().getValue());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_exam, container, false);
        recyclerView = root.findViewById(R.id.examContainer);
        addButton = root.findViewById(R.id.addExamButton);
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
        View dialogView = inflater.inflate(R.layout.examlayout, null);

        // Set up the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView);
        builder.setTitle("Add Course");

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the course when the "Add" button in the dialog is clicked
                examDateInput = dialogView.findViewById(R.id.examDateInput);
                examTimeInput = dialogView.findViewById(R.id.examTimeInput);
                examLocationInput = dialogView.findViewById(R.id.examLocationInput);

                String examDate = examDateInput.getText().toString().trim();
                String examTime= examTimeInput.getText().toString().trim();
                String examLoc = examLocationInput.getText().toString().trim();


                // Validate the date format
                if (!isValidDateFormat(examDate)) {
                    Toast.makeText(getContext(), "Invalid date format. Please use MM-DD-YY", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate if the date contains only numbers
                if (!examDate.matches("^(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])-\\d{2}$"))  {
                    Toast.makeText(getContext(), "Invalid date format. Please use numbers only", Toast.LENGTH_SHORT).show();
                   return;
               }
                if (!examLoc.isEmpty() && !examDate.isEmpty()
                        && !examTime.isEmpty()) {
                        adapter.addAssign(examDate, examTime, examLoc);

                        //Sort it based on Date
                        adapter.sortAssignmentsByDueDate();
                        //Set the Adapter
                        recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(getContext(), "Exam fields can not be empty", Toast.LENGTH_SHORT).show();
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
        View dialog = inflater.inflate(R.layout.examlayout, null);
        //setUp the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialog);
        builder.setTitle("Edit the Course");
        examDateInput = dialog.findViewById(R.id.examDateInput);
        examTimeInput = dialog.findViewById(R.id.examTimeInput);
        examLocationInput = dialog.findViewById(R.id.examLocationInput);
        String examDate =examDateInput.getText().toString().trim();

        //Action buttons
        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Validate the date format
                if (!isValidDateFormat(examDate)) {
                    Toast.makeText(getContext(), "Invalid date format. Please use MM-DD-YY", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate if the date contains only numbers
                if (!examDate.matches("^(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])-\\d{2}$"))  {
                    Toast.makeText(getContext(), "Invalid date format. Please use numbers only", Toast.LENGTH_SHORT).show();
                    return;
                }
                adapter.editExam(examDateInput.getText().toString().trim(),
                        examTimeInput.getText().toString().trim(),
                        examLocationInput.getText().toString().trim());
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
        builder.setTitle("Delete Exam");
        builder.setMessage("Are you sure you want to delete this Exam?");
        //Action buttons
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //delete the course
                adapter.deleteExam();
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

    // Method to validate date format
    private boolean isValidDateFormat(String date) {
        return date.matches("\\d{2}-\\d{2}-\\d{2}");
    }
}