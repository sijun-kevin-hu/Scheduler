package com.example.scheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;
    private ToDoAdapter adapter;
    private FloatingActionButton addButton;
    private TextInputEditText toDodateInput, toDoTypeInput, toDODiscrInput;
    private LayoutInflater inflater;
    private NavigationView navigationView;
    public List<ToDoList> toDoList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toDoList = new ArrayList<>();
        adapter = new ToDoAdapter(toDoList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = root.findViewById(R.id.notifContainer);
        this.inflater = inflater;

        // Initialize RecyclerView layout manager and adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //registering the context menu
        registerForContextMenu(recyclerView);

        return root;
    }


}