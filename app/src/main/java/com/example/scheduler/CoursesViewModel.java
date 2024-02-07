package com.example.scheduler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.scheduler.Course;
import java.util.ArrayList;
import java.util.List;

public class CoursesViewModel extends ViewModel {
    private MutableLiveData<List<Course>> coursesLiveData;

    public MutableLiveData<List<Course>> getCoursesLiveData() {
        if (coursesLiveData == null) {
            coursesLiveData = new MutableLiveData<>();
            coursesLiveData.setValue(new ArrayList<>());
        }
        return coursesLiveData;
    }

}
