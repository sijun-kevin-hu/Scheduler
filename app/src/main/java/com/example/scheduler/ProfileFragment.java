package com.example.scheduler;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private String firstName;
    private String lastName;

    //My code
    private ImageView profileImageView;
    private Button selectImageButton, updateButton;
    private TextView profileTextView;
    private TextInputEditText firstNameEditText, lastNameEditText;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate profile fragment and set the inputs
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        firstNameEditText = root.findViewById(R.id.firstNameInput);
        lastNameEditText = root.findViewById(R.id.lastNameInput);
        updateButton= root.findViewById(R.id.updateButton);

        //inflate header fragment
        View rootview = inflater.inflate(R.layout.fragment_header, container, false);
        profileTextView = rootview.findViewById(R.id.profileName);
        drawerLayout = requireActivity().findViewById(R.id.drawerLayout);

        //inflate navigation view
        navigationView = requireActivity().findViewById(R.id.navigationView);

        //listening to update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameEditText.getText().toString().trim();
                lastName = lastNameEditText.getText().toString().trim();
                if(!firstName.isEmpty() && !lastName.isEmpty()) {
                    ProfileFragmentDirections.ActionProfileToLayoutHeader transfer =
                            ProfileFragmentDirections.actionProfileToLayoutHeader(firstName, lastName);
                    transfer.setFirstName(firstName);
                    transfer.setLastName(lastName);
                    NavHostFragment.findNavController(ProfileFragment.this).navigate(transfer);
                }
                // Remove the existing header view before adding the updated one
                if (navigationView.getHeaderCount() > 0) {
                    navigationView.removeHeaderView(navigationView.getHeaderView(0));
                }

                // Add the updated header view
                View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_header, navigationView, false);
                profileTextView = headerView.findViewById(R.id.profileName);
                profileTextView.setText(firstName + " " + lastName);
                navigationView.addHeaderView(headerView);

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        return root;
    }

}