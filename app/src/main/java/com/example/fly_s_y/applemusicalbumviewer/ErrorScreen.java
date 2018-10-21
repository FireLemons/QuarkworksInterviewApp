package com.example.fly_s_y.applemusicalbumviewer;

import android.support.v4.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fly_s_y.applemusicalbumviewer.databinding.ErrorScreenBinding;

public class ErrorScreen extends Fragment {
    private ErrorScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.error_screen, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        binding.setError(((MusicListView)getActivity()).getError());
    }
}
