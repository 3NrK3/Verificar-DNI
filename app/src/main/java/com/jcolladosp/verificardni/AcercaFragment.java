package com.jcolladosp.verificardni;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AcercaFragment extends BaseFragment {


    public static AcercaFragment newInstance() {
        AcercaFragment fragment = new AcercaFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    public AcercaFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acerca, container, false);
    }




}
