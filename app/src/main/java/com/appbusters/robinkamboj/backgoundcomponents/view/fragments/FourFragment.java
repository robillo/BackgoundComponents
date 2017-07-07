package com.appbusters.robinkamboj.backgoundcomponents.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.appbusters.robinkamboj.backgoundcomponents.R;
import com.appbusters.robinkamboj.backgoundcomponents.controller.AlarmManagerBroadcastReceiver;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends Fragment {

    private AlarmManagerBroadcastReceiver alarm;
    Button startRepeat, cancel, startSingle;

    public FourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View v = inflater.inflate(R.layout.fragment_four, container, false);

        startRepeat = (Button) v.findViewById(R.id.start_repeat);
        cancel = (Button) v.findViewById(R.id.cancel);
        startSingle = (Button) v.findViewById(R.id.start_single);
        alarm = new AlarmManagerBroadcastReceiver();

        startRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRepeatingAlarm();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelRepeatingTimer();
            }
        });

        startSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onetimeTimer();
            }
        });

        return v;
    }

    public void startRepeatingAlarm(){
        Context context = getActivity().getApplicationContext();
        if(alarm != null){
            alarm.SetAlarm(context);
        }
        else {
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelRepeatingTimer(){
        Context context = getActivity().getApplicationContext();
        if(alarm != null){
            alarm.CancelAlarm(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void onetimeTimer(){
        Context context = getActivity().getApplicationContext();
        if(alarm != null){
            alarm.setOnetimeTimer(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }
}
