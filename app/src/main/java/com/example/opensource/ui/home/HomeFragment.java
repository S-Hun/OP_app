package com.example.opensource.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.opensource.R;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3", "LIST3", "LIST3", "LIST3", "LIST3", "LIST3", "LIST3", "LIST3", "LIST3", "LIST3", "LIST3", "LIST3", "LIST3"};
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);


        // 파일목록 불러오기
        File getList = new File(getContext().getFilesDir().getAbsolutePath());
        String files[] = getList.list();

        final ArrayList<ListMenuObject> listMenu = new ArrayList<>();

        for (String fn : files) {
            ListMenuObject data = new ListMenuObject(getTitle(fn), fn);

            Log.d("here",data.title);
            listMenu.add(data);
        }

        CustomAdapter adapter = new CustomAdapter(getActivity(), R.layout.row, listMenu);
        ListView listview = (ListView) view.findViewById(R.id.homeList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), HomeListDetail.class);
                intent.putExtra("filename", listMenu.get(position).getFilename());
                view.getContext().startActivity(intent);
            }
        });


        return view;
    }

    public String getTitle(String filename) {
        String temp[] = filename.split("_");
        String date = temp[0];
        String[] timeArr = temp[1].split("-");
        String time = timeArr[0] + ":" + timeArr[1] + ":" + timeArr[2];

        return date + " " + time;
    }

    class ListMenuObject {
        private String title;
        private String filename;

        public String getTitle() {
            Log.d("title",this.title);
            return this.title;
        }

        public String getFilename() {
            return this.filename;
        }

        public ListMenuObject(String title, String filename) {
            this.filename = filename;
            this.title = title;
        }
    }

    class CustomAdapter extends ArrayAdapter<ListMenuObject> {

        private LayoutInflater inflater;
        private ArrayList<ListMenuObject> data;
        private int layout;

        public CustomAdapter(Context context, int layout, ArrayList<ListMenuObject> data) {
            super(context, layout, data);
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.row, null);
            }
            ListMenuObject item = data.get(position);
            if (item != null) {
                TextView title = (TextView)v.findViewById(R.id.title);
                title.setText(item.getTitle());
            }
            return v;
        }
    }

}
