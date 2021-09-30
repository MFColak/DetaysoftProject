package com.android.mfcolak.detaysoftproject.service;

import com.android.mfcolak.detaysoftproject.model.response.TodoResponse;
import com.android.mfcolak.detaysoftproject.model.response.UserResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetaysoftApiService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String BASE_URL_profil ="https://randomuser.me/";

    private DetaysoftAPI todoapi;
    private DetaysoftAPI profilapi;

    public DetaysoftApiService(){
        todoapi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DetaysoftAPI.class);

        profilapi = new Retrofit.Builder()
                .baseUrl(BASE_URL_profil)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DetaysoftAPI.class);
    }
    public Single<List<TodoResponse>> getTodos(){
        return todoapi.getTodos();
    }

    public Single<UserResponse> getProfile(){
        return profilapi.getProfile();
    }
}
