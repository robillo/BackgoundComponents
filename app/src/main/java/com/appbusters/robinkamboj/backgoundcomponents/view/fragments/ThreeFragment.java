package com.appbusters.robinkamboj.backgoundcomponents.view.fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.appbusters.robinkamboj.backgoundcomponents.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {

    Button downloadButton;
    @SuppressWarnings("unused")
    private ProgressDialog prgDialog;

    // Progress Dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;

    // Music resource URL
    private static String file_url = "http://programmerguru.com/android-tutorial/wp-content/uploads/2014/01/jai_ho.mp3";

    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View v = inflater.inflate(R.layout.fragment_three, container, false);

        downloadButton = (Button) v.findViewById(R.id.download);

        checkForPermissions();

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadButton.setEnabled(false);
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/jai_ho.mp3");
                if(permissionsGranted()){
                    if(file.exists()){
                        Toast.makeText(getActivity(), "File already exist under SD card, playing Music", Toast.LENGTH_SHORT).show();
                        // Play Music
                        playMusic();
                    }
                    else {
                        Toast.makeText(getActivity(), "File doesn't exist under SD Card, downloading Mp3 from Internet", Toast.LENGTH_SHORT).show();
                        // Trigger Async Task (onPreExecute method)
                        new DownloadMusicFromInternet().execute(file_url);
                    }
                }
                else checkForPermissions();
            }
        });

        return v;
    }

    private void checkForPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                getActivity().requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 5);
            }
        }
    }

    private boolean permissionsGranted() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    // Play Music
    protected void playMusic(){
        // Read Mp3 file present under SD card
        Uri myUri1 = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/jai_ho.mp3");
        MediaPlayer player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            player.setDataSource(getActivity(), myUri1);
            player.prepare();
            // Start playing the Music file
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    // Once Music is completed playing, enable the button
                    downloadButton.setEnabled(true);
                    Toast.makeText(getActivity(), "Music completed playing", Toast.LENGTH_LONG).show();
                }
            });
        } catch (IllegalArgumentException e) {
            Toast.makeText(getActivity(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(getActivity(), "URI cannot be accessed, permissed needed", Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            Toast.makeText(getActivity(), "Media Player is not in correct state", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getActivity(), "IO Error occured", Toast.LENGTH_LONG).show();
        }
    }

    private class DownloadMusicFromInternet extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // Get Music file length
                int lenghtOfFile = conection.getContentLength();
                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(),10*1024);
                // Output stream to write file in SD card
                OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()+"/jai_ho.mp3");
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // Publish the progress which triggers onProgressUpdate method
                    publishProgress(String.valueOf((int) ((total * 100) / lenghtOfFile)));

                    // Write data to file
                    output.write(data, 0, count);
                }
                // Flush output
                output.flush();
                // Close streams
                output.close();
                input.close();
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Shows Progress Bar Dialog and then call doInBackground method
            showDialog();
        }

        @Override
        protected void onPostExecute(String s) {
            // Dismiss the dialog after the Music file was downloaded
            //noinspection deprecation
            dismissDialog();
            Toast.makeText(getActivity(), "Download complete, playing Music", Toast.LENGTH_LONG).show();
            // Play the music
            playMusic();
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            // Set progress percentage
            Log.e("PROGRESS", progress[0]);
            incrementProgress(Integer.parseInt(progress[0]));
        }
    }

    private void incrementProgress(int value){
        prgDialog.setProgress(value);
    }

    private void dismissDialog(){
        prgDialog.dismiss();
    }

    private void showDialog() {
        prgDialog = new ProgressDialog(getActivity());
        prgDialog.setMessage("Downloading Mp3 file. Please wait...");
        prgDialog.setIndeterminate(false);
        prgDialog.setMax(100);
        prgDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        prgDialog.setCancelable(false);
        prgDialog.show();
    }
}
