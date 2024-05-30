package com.example.account_project_evaluation.ApiHelper;

import com.google.gson.JsonElement;

public interface ApiResponseListner {

    public void success(String tag, JsonElement jsonElement);
    public void failure(String tag, String errorMessage);
}
