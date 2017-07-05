package com.appbusters.robinkamboj.backgoundcomponents.view.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appbusters.robinkamboj.backgoundcomponents.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {

    EditText time;
    Button startMyAsync;
    TextView result;

    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);

        View v = inflater.inflate(R.layout.fragment_two, container, false);
        time = (EditText) v.findViewById(R.id.time);
        startMyAsync = (Button) v.findViewById(R.id.start);
        result = (TextView) v.findViewById(R.id.result);

        startMyAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAsyncTaskRunner runner = new MyAsyncTaskRunner();
                String sleepTime = time.getText().toString();
                runner.execute(sleepTime);
            }
        });

        return v;
    }

    private class MyAsyncTaskRunner extends AsyncTask<String, String, String>{

        String resp;
        ProgressDialog dialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping...");       //Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;
                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            }
            catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(getActivity(), "ProgressDialog", "Wait for "+time.getText().toString()+ " seconds");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(dialog == null){
                dialog = ProgressDialog.show(getActivity(), "ProgressDialog", "Wait for "+time.getText().toString()+ " seconds");
            }
            result.setText(values[0]);

        }

        @Override
        protected void onPostExecute(String finalResult) {
            // execution of result of Long time consuming operation
            if(dialog!=null){
                dialog.dismiss();
            }
            result.setText(finalResult);
        }
    }
}
