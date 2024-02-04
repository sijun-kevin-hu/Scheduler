package com.example.scheduler;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String firstName;
    private String lastName;

    //My code
    private ImageView profileImageView;
    private Button selectImageButton, updateButton;
    private TextView profileTextView;
    private TextInputEditText firstNameEditText, lastNameEditText;
    private DrawerLayout drawerLayout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            firstName = getArguments().getString(ARG_PARAM1);
            lastName= getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        View rootview = inflater.inflate(R.layout.layout_header, container, false);
        firstNameEditText = root.findViewById(R.id.firstNameInput);
        lastNameEditText = root.findViewById(R.id.lastNameInput);
        updateButton= root.findViewById(R.id.updateButton);
        profileTextView = rootview.findViewById(R.id.profileName);
        drawerLayout = requireActivity().findViewById(R.id.drawerLayout);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameEditText.getText().toString().trim();
                lastName = lastNameEditText.getText().toString().trim();
                profileTextView.setText(getString(R.string.profileName,firstName ,lastName));
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        return root;
    }
}