package com.example.scheduler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoursesFragment extends Fragment implements View.OnCreateContextMenuListener{
    private RecyclerView recyclerView;
    private CoursesAdapter adapter;
    private FloatingActionButton addButton;
    private TextInputEditText courseNameInput, instructorNameInput, timeInput;
    private LayoutInflater inflater;
    private CoursesViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initializing the viewModel
        viewModel = new ViewModelProvider(this).get(CoursesViewModel.class);
        adapter = new CoursesAdapter(viewModel.getCoursesLiveData().getValue());
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_courses, container, false);
        recyclerView = root.findViewById(R.id.classContainer);
        addButton = root.findViewById(R.id.addClassButton);
        this.inflater = inflater;

        // Initialize RecyclerView layout manager and adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //registering the context menu
        registerForContextMenu(recyclerView);
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
        View dialogView = inflater.inflate(R.layout.class_modification, null);

        // Set up the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView);
        builder.setTitle("Add Course");

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the course when the "Add" button in the dialog is clicked
                courseNameInput = dialogView.findViewById(R.id.courseTitleInput);
                instructorNameInput = dialogView.findViewById(R.id.InstructorNameInput);
                timeInput = dialogView.findViewById(R.id.courseTimeInput);

                String courseName = courseNameInput.getText().toString().trim();
                String instructorName = instructorNameInput.getText().toString().trim();
                String time = timeInput.getText().toString().trim();
                if (!courseName.isEmpty() && !instructorName.isEmpty()
                        && !time.isEmpty()) {;
                        adapter.addCourse(courseName, instructorName, time);
                        recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(getContext(), "Course name cannot be empty", Toast.LENGTH_SHORT).show();
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
        View dialog = inflater.inflate(R.layout.class_modification, null);
        //setUp the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialog);
        builder.setTitle("Edit the Course");
        courseNameInput = dialog.findViewById(R.id.courseTitleInput);
        instructorNameInput = dialog.findViewById(R.id.InstructorNameInput);
        timeInput = dialog.findViewById(R.id.courseTimeInput);

        //Action buttons
        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.editCourse(courseNameInput.getText().toString().trim(),
                                   instructorNameInput.getText().toString().trim(),
                                   timeInput.getText().toString().trim());

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
                builder.setMessage("Are you sure you want to delete this course?");
        //Action buttons
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //delete the course
                 adapter.deleteCourse();
                 adapter.notifyItemRemoved(position);
                recyclerView.setAdapter(adapter);
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
