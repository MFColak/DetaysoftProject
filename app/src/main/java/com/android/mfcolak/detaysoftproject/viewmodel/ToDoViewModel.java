package com.android.mfcolak.detaysoftproject.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.mfcolak.detaysoftproject.model.response.TodoResponse;
import com.android.mfcolak.detaysoftproject.service.DetaysoftApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ToDoViewModel extends AndroidViewModel {
    public MutableLiveData<List<TodoResponse>> todos = new MutableLiveData<>();
    public MutableLiveData<Boolean> todoLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading =new MutableLiveData<Boolean>();

    private DetaysoftApiService todosService = new DetaysoftApiService();
    private CompositeDisposable disposable = new CompositeDisposable();

    public ToDoViewModel(Application application){
        super(application);
    }
    public void reflesh(){
        fetchFromRemote();
    }
    private void fetchFromRemote(){
        loading.setValue(true);
        disposable.add(
        todosService.getTodos()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<TodoResponse>>() {
                            @Override
                            public void onSuccess(List<TodoResponse> toDosAbouts) {
                                todos.setValue(toDosAbouts);
                                todoLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                todoLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
