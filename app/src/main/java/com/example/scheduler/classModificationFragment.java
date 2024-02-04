package com.example.scheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link classModificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class classModificationFragment extends Fragment {
    private String courseTitle, instructorName, time;
    private Button addButton;
    private View dialogView;
    private TextInputEditText  courseNameInput,
            instructorNameInput,timeInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the classModification layout
        View root = inflater.inflate(R.layout.class_modification, container, false);
        courseTitle = root.findViewById(R.id.courseTitleInput).toString().trim();
        instructorName = root.findViewById(R.id.InstructorNameInput).toString().trim();
        time = root.findViewById(R.id.courseTimeInput).toString().trim();

        // Inflate the dialog layout
        dialogView = inflater.inflate(R.layout.class_modification, null);

        return root;
    }

}