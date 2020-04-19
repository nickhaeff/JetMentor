package com.example.jetmentor.ui.forum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForumViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ForumViewModel() {

    }

    public LiveData<String> getText() {
        return mText;
    }
}