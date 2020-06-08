package com.example.opensource.ui.measure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.opensource.R;

public class MeasureFragment extends Fragment {

    private MeasureViewModel measureViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        measureViewModel =
                ViewModelProviders.of(this).get(MeasureViewModel.class);
        View root = inflater.inflate(R.layout.fragment_measure, container, false);
        final TextView textView = root.findViewById(R.id.text_measure);
        measureViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
