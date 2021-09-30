package com.android.mfcolak.detaysoftproject.model.response;

import com.google.gson.annotations.SerializedName;

public class TodoResponse {
    @SerializedName("userId")
    public String userId;

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("completed")
    public Boolean completed;




}
