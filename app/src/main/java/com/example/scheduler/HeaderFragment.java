package com.example.scheduler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scheduler.databinding.FragmentHeaderBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeaderFragment extends Fragment {

        private FragmentHeaderBinding binding;
        private TextView profileName;
        private ImageView profileImageView;
        private DrawerLayout drawerLayout;
        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState
        )
        {

            binding = FragmentHeaderBinding.inflate(inflater, container, false);
            profileImageView = binding.profileImageView;
            profileName= binding.profileName;

            //inflating drawerLayout
            drawerLayout = requireActivity().findViewById(R.id.drawerLayout);
            return binding.getRoot();

        }

        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            String Firstname = HeaderFragmentArgs.fromBundle(getArguments()).getFirstName().toString();
            String LastName = HeaderFragmentArgs.fromBundle(getArguments()).getLastName().toString();
            profileName.setText(Firstname + " "+ LastName);
            profileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(HeaderFragment.this)
                            .navigate(R.id.action_layout_header_to_profile);
                   drawerLayout.closeDrawer(GravityCompat.START);
                }
            });
        }
        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
}