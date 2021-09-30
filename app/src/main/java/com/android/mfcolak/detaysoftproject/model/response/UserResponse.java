package com.android.mfcolak.detaysoftproject.model.response;

import java.util.List;

import com.android.mfcolak.detaysoftproject.model.Info;
import com.android.mfcolak.detaysoftproject.model.ResultsItem;
import com.google.gson.annotations.SerializedName;

public class UserResponse{

	@SerializedName("results")
	private List<ResultsItem> results;

	@SerializedName("info")
	private Info info;

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public void setInfo(Info info){
		this.info = info;
	}

	public Info getInfo(){
		return info;
	}
}