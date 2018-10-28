package com.example.fly_s_y.Error;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.fly_s_y.applemusicalbumviewer.BR;

/**
 * Model class for the error modal
 */
public class ErrorDisplay extends BaseObservable {
    private boolean isVisible, isError;
    private String message;

    public ErrorDisplay(){
        isVisible = false;
        message = "";
    }

    public ErrorDisplay(String message){
        this.isVisible = false;
        this.message = message;
    }

    public ErrorDisplay(boolean isVisible, String message){
        this.isVisible = isVisible;
        this.message = message;
    }

    public ErrorDisplay(boolean isError, boolean isVisible, String message){
        this.isError = isError;
        this.isVisible = isVisible;
        this.message = message;
    }

    @Bindable
    public boolean getIsVisible(){
        return isVisible;
    }

    @Bindable
    public boolean getIsError(){ return isError; }

    @Bindable
    public String getMessage(){
        return message;
    }


    public void clearError(){
        isVisible = false;
        message = "";
        notifyPropertyChanged(BR.isVisible);
        notifyPropertyChanged(BR.message);
    }

    /**
     * Display an error via the error modal
     * @param error A string describing the cause of the error
     */
    public void setError(String error) {
        this.message = error;
        this.isVisible = true;
        notifyPropertyChanged(BR.message);
        notifyPropertyChanged(BR.isVisible);

        if(!this.isError){
            this.isError = true;
            notifyPropertyChanged(BR.isError);
        }
    }

    /**
     * Displays a warning via the error modal
     * @param warning A string describing why the user is seeing the warning
     */
    public void setWarning(String warning){
        this.message = warning;
        this.isVisible = true;
        notifyPropertyChanged(BR.message);
        notifyPropertyChanged(BR.isVisible);

        if(this.isError){
            this.isError = false;
            notifyPropertyChanged(BR.isError);
        }
    }
}