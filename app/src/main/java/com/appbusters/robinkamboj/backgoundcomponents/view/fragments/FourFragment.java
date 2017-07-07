package com.appbusters.robinkamboj.backgoundcomponents.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appbusters.robinkamboj.backgoundcomponents.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends Fragment {

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

        return v;
    }

}
