package com.example.opensource.ui.measure;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MeasureViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MeasureViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("운동 자세를 촬영해 올바른 자세인지 판별해드립니다.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}