package com.example.fly_s_y.Error;

import android.support.v4.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fly_s_y.applemusicalbumviewer.MusicListView;
import com.example.fly_s_y.applemusicalbumviewer.R;
import com.example.fly_s_y.applemusicalbumviewer.databinding.ErrorScreenBinding;

public class ErrorModalFragment extends Fragment implements View.OnClickListener {
    private ErrorScreenBinding binding;
    private ErrorDisplay errorModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.error_screen, container, false);

        View errorView = binding.getRoot();
        Button dismissButton = (Button)errorView.findViewById(R.id.dismissButton);

        dismissButton.setOnClickListener(this);

        return errorView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        binding.setError(errorModel = ((MusicListView)getActivity()).getError());
    }

    @Override
    public void onClick(View view) {
        errorModel.clearError();
    }
}
