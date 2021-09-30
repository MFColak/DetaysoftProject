package com.android.mfcolak.detaysoftproject.service;

import com.android.mfcolak.detaysoftproject.model.response.TodoResponse;
import com.android.mfcolak.detaysoftproject.model.response.UserResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface DetaysoftAPI {
    @GET("todos")
    Single<List<TodoResponse>> getTodos();

    @GET("api")
    Single<UserResponse> getProfile();
}
