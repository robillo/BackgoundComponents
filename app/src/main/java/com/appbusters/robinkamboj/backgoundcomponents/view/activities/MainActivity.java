package com.appbusters.robinkamboj.backgoundcomponents.view.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.appbusters.robinkamboj.backgoundcomponents.view.fragments.FiveFragment;
import com.appbusters.robinkamboj.backgoundcomponents.view.fragments.FourFragment;
import com.appbusters.robinkamboj.backgoundcomponents.view.fragments.MainFragment;
import com.appbusters.robinkamboj.backgoundcomponents.R;
import com.appbusters.robinkamboj.backgoundcomponents.view.fragments.ThreeFragment;
import com.appbusters.robinkamboj.backgoundcomponents.view.fragments.TwoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(null == savedInstanceState){
            initFragment(1);
        }
    }

    void onClick(View v){
        switch (v.getId()){
            case R.id.one:{
                initFragment(1);
                break;
            }
            case R.id.two:{
                initFragment(2);
                break;
            }
            case R.id.three:{
                initFragment(3);
                break;
            }
            case R.id.four:{
                initFragment(4);
                break;
            }
            case R.id.five:{
                initFragment(5);
                break;
            }
        }
    }

    private void initFragment(int which){
        switch (which){
            case 1:{
                Fragment fragment = new MainFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.fragment_container, fragment);
                transaction.commit();
                break;
            }
            case 2:{
                Fragment fragment = new TwoFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.fragment_container, fragment);
                transaction.commit();
                break;
            }
            case 3:{
                Fragment fragment = new ThreeFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.fragment_container, fragment);
                transaction.commit();
                break;
            }
            case 4:{
                Fragment fragment = new FourFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.fragment_container, fragment);
                transaction.commit();
                break;
            }
            case 5:{
                Fragment fragment = new FiveFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.fragment_container, fragment);
                transaction.commit();
                break;
            }
        }
    }
}
