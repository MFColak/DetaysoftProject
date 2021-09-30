package com.android.mfcolak.detaysoftproject.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.mfcolak.detaysoftproject.model.response.TodoResponse;
import com.android.mfcolak.detaysoftproject.model.response.UserResponse;
import com.android.mfcolak.detaysoftproject.service.DetaysoftApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class ProfileViewModel extends AndroidViewModel {
    public MutableLiveData<UserResponse> profilLiveData = new MutableLiveData<>();
    private DetaysoftApiService profileService = new DetaysoftApiService();
    private CompositeDisposable disposable = new CompositeDisposable();

    public ProfileViewModel(Application application) {
        super(application);
    }

    public void fetch() {

        disposable.add(
                profileService.getProfile()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<UserResponse>() {
                            @Override
                            public void onSuccess(UserResponse userResponse) {
                                profilLiveData.setValue(userResponse);
                            }
                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }
                        })
        );

    }


}
