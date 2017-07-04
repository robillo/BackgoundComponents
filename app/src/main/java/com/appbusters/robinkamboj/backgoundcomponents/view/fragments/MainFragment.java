package com.appbusters.robinkamboj.backgoundcomponents.view.fragments;


import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.appbusters.robinkamboj.backgoundcomponents.R;
import com.appbusters.robinkamboj.backgoundcomponents.controller.PlayService;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        Button startButton = (Button) v.findViewById(R.id.start_service);
        Button stopButton = (Button) v.findViewById(R.id.stop_service);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startService(new Intent(getActivity(), PlayService.class));
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startService(new Intent(getActivity(), PlayService.class));
            }
        });

        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> list = manager.getRunningServices(50);
        for (int i = 0; i < list.size(); i++) {
            ActivityManager.RunningServiceInfo listItem = list.get(i);
            Toast.makeText(getActivity().getApplicationContext(), "Service Process " + listItem.process + " with component " + listItem.service.getClassName(), Toast.LENGTH_SHORT).show();
        }

        return v;
    }

}
