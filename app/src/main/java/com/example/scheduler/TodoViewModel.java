package com.example.scheduler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class TodoViewModel extends ViewModel {
    private MutableLiveData<List<ToDoList>> listMutableLiveData;

    public MutableLiveData<List<ToDoList>> getListMutableLiveData(){
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            listMutableLiveData.setValue(new ArrayList<>());
        }
        return listMutableLiveData;
    }
}
