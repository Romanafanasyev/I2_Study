package com.example.i2study.http.response;


public class GroupByIdResponse {

    private int id;
    private String name;
    private int universityId;

    public int getGroupId(){
        return id;
    }

    public void setGroupId(int id){
        this.id = id;
    }

    public String getGroupName(){
        return name;
    }

    public void setGroupName(String name){
        this.name = name;
    }

}
