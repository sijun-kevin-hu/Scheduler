package com.example.scheduler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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
    private Course classes;
    private LinearLayout coursesContainer;
    private Button addButton;
    private LayoutInflater inflator;
    private TextInputEditText courseNameInput, instructorNameInput,
                              timeInput;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          courses = new Catalog<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_courses, container, false);
        coursesContainer = root.findViewById(R.id.classContainer);
        addButton = root.findViewById(R.id.addClassButton);
        inflator = LayoutInflater.from(requireContext());
        this.inflator = inflater;
        View classModification = inflater.inflate(R.layout.class_modification, null);
        courseNameInput = classModification.findViewById(R.id.courseTitleInput);
        instructorNameInput = classModification.findViewById(R.id.InstructorNameInput);
        timeInput = classModification.findViewById(R.id.courseTimeInput);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call add new courses method

                addNewCourses();
            }
        });
        return root;
    }
    public void addNewCourses(){
        TextView newCourseTextView = new TextView(getContext());
        newCourseTextView.setText(courseNameInput.getText().toString());
    }


}