package com.facebook.okhttp_pract_get;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        String contentType = "Unknown";

        if (request.body() != null) {
            contentType = request.body().contentType().toString();
        }

        boolean isFormUrlEncoded = contentType.contains("application/x-www-form-urlencoded");

        if (isFormUrlEncoded) {
            if (response.isSuccessful()) {
                Log.d("ErrorInterceptor", "You are posting correct data.");
            } else {
                Log.d("ErrorInterceptor", "There was an error in your post request. Response code: " + response.code());
            }
        } else {
            Log.d("ErrorInterceptor", "You are posting in the wrong format.");
        }

        return response;
    }
}
