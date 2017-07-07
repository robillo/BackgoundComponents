package com.appbusters.robinkamboj.backgoundcomponents.view.fragments;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.appbusters.robinkamboj.backgoundcomponents.R;
import com.appbusters.robinkamboj.backgoundcomponents.controller.LocalWordService;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FiveFragment extends Fragment implements ServiceConnection{

    Button updateList, triggerService;
    ListView listView;
    LocalWordService service;
    List<String> wordList;
    ArrayAdapter<String> adapter;

    public FiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View v = inflater.inflate(R.layout.fragment_five, container, false);
        updateList = (Button) v.findViewById(R.id.updateList);
        triggerService = (Button) v.findViewById(R.id.triggerServiceUpdate);
        listView = (ListView) v.findViewById(R.id.list);
        wordList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, wordList);
        listView.setAdapter(adapter);

        updateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (service != null) {
                    Toast.makeText(getActivity(), "Number of elements" + service.getWordList().size(), Toast.LENGTH_SHORT).show();
                    wordList.clear();
                    wordList.addAll(service.getWordList());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        triggerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent service = new Intent(getActivity().getApplicationContext(), LocalWordService.class);
                getActivity().getApplicationContext().startService(service);
            }
        });

        return v;
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        LocalWordService.MyBinder binder = (LocalWordService.MyBinder) iBinder;
        service = binder.getService();
        Toast.makeText(getActivity(), "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        service = null;
    }
}
