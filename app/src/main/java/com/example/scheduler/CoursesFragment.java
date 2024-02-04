package com.example.scheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoursesFragment extends Fragment {
    private Catalog<String> courses;
    private RecyclerView recyclerView;
    private CoursesAdapter adapter;
    private Button addButton;
    private TextInputEditText courseNameInput, instructorNameInput, timeInput;
    private LayoutInflater inflater;
    private NavigationView navigationView;
    public List<String> coursesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courses = new Catalog<>();
        coursesList = new ArrayList<>();
        adapter = new CoursesAdapter(coursesList);
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
                if (!courseName.isEmpty()) {
                    for (int i=0; i<8; i++){
                        adapter.addCourse(courseName);
                        recyclerView.setAdapter(adapter);
                    }
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
}
