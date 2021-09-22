package com.example.navigation.ui.fragment4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.navigation.MainActivity;
import com.example.navigation.R;
import com.example.navigation.Toast;
import com.example.navigation.databinding.FragmentFragment4Binding;
import com.example.navigation.databinding.FragmentHomeBinding;
import com.example.navigation.ui.home.HomeViewModel;

import org.jetbrains.annotations.NotNull;

public class Fragment4 extends Fragment {

    private FragmentViewModel fragmentViewModel;
    private @NonNull FragmentFragment4Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fragmentViewModel =
                new ViewModelProvider(this).get(FragmentViewModel.class);

        binding = FragmentFragment4Binding.inflate(inflater,container,false);
        View root = binding.getRoot();

        final TextView textView = binding.textView3;
        fragmentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}