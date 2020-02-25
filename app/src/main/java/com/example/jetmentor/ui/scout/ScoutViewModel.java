package com.example.jetmentor.ui.scout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoutViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ScoutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Mentors are found here.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}