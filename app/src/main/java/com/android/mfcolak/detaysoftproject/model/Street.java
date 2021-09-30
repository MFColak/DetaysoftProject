package com.android.mfcolak.detaysoftproject.model;

import com.google.gson.annotations.SerializedName;

public class Street{

	@SerializedName("number")
	private int number;

	@SerializedName("name")
	private String name;

	public void setNumber(int number){
		this.number = number;
	}

	public int getNumber(){
		return number;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}