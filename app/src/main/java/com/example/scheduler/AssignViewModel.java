package com.example.scheduler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.scheduler.Course;
import java.util.ArrayList;
import java.util.List;

public class AssignViewModel extends ViewModel {
    private MutableLiveData<List<Assignment>> assignLiveData;

    public MutableLiveData<List<Assignment>> getAssignLiveData() {
        if (assignLiveData == null) {
            assignLiveData = new MutableLiveData<>();
            assignLiveData.setValue(new ArrayList<>());
        }
        return assignLiveData;
    }

}