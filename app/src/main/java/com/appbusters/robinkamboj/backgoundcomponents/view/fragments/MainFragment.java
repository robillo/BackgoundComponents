package com.appbusters.robinkamboj.backgoundcomponents.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appbusters.robinkamboj.backgoundcomponents.R;
import com.appbusters.robinkamboj.backgoundcomponents.controller.PlayService;


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

        return v;
    }

}
