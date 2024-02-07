package com.example.scheduler;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExamViewModel extends ViewModel {
    private MutableLiveData<List<Exam>> examLiveData;

    public MutableLiveData<List<Exam>> getExamLiveData() {
        if (examLiveData == null) {
            examLiveData = new MutableLiveData<>();
            examLiveData.setValue(new ArrayList<>());
        }
        return examLiveData;
    }
}
