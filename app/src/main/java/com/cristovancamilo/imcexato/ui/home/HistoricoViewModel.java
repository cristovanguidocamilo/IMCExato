package com.cristovancamilo.imcexato.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoricoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HistoricoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Histórico");
    }

    public LiveData<String> getText() {
        return mText;
    }
}