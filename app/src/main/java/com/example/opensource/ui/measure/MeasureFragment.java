package com.example.opensource.ui.measure;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.opensource.R;

public class MeasureFragment extends Fragment {

    private MeasureViewModel measureViewModel;
    static final String[] MEASURE_LIST_MENU = {"스쿼트","런지","푸쉬업"} ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_measure, null) ;

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, MEASURE_LIST_MENU) ;

        ListView listview = (ListView) view.findViewById(R.id.measureList);
        listview.setAdapter(adapter) ;
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), MeasureActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }
}
