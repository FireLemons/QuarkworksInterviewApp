package com.example.fly_s_y.applemusicalbumviewer;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class ErrorDisplay extends BaseObservable {
    private boolean isVisible;
    private String errorMessage;

    public ErrorDisplay(){
        isVisible = false;
        errorMessage = "";
    }

    public ErrorDisplay(String errorMessage){
        isVisible = false;
        this.errorMessage = errorMessage;
    }

    public ErrorDisplay(boolean isVisible, String errorMessage){
        this.isVisible = isVisible;
        this.errorMessage = errorMessage;
    }

    @Bindable
    public boolean getIsVisible(){
        return isVisible;
    }

    @Bindable
    public String getErrorMessage(){
        return errorMessage;
    }

    public void clearError(){
        errorMessage = "";
        isVisible = false;
        notifyPropertyChanged(BR.isVisible);
    }

    public void setError(String errorMessage) {
        this.errorMessage = errorMessage;
        isVisible = true;
        notifyPropertyChanged(BR.errorMessage);
        notifyPropertyChanged(BR.isVisible);
    }
}
