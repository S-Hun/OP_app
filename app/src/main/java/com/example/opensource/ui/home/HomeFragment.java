package com.example.opensource.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.opensource.R;

public class HomeFragment extends Fragment {

    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3","LIST3","LIST3","LIST3","LIST3","LIST3","LIST3","LIST3","LIST3","LIST3","LIST3","LIST3","LIST3"} ;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null) ;

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU) ;

        ListView listview = (ListView) view.findViewById(R.id.homeList);
        listview.setAdapter(adapter) ;

        return view ;
    }
}
