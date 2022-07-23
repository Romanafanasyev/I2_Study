package com.example.i2study;

public class urlStorage {

    private static String path = "http://192.168.18.38:5000/";
    private static String pathApi = path + "api/";

    public static String getPath() {
        return path;
    }

    public static String getPathApi() {
        return pathApi;
    }
}
