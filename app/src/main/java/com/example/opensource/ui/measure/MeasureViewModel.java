package com.example.opensource.ui.measure;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MeasureViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MeasureViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("측정");
    }

    public LiveData<String> getText() {
        return mText;
    }
}