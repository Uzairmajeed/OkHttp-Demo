package com.facebook.okhttp_pract_get;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkOperations {
    private OkHttpClient client;

    public NetworkOperations() {
        client = new OkHttpClient.Builder()
                .addInterceptor(new ErrorInterceptor())
                .build();
    }

    public void performGetRequest(String url, final NetworkCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                callback.onError(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    callback.onSuccess(myResponse);
                }
            }
        });
    }

    public void performPostRequest(String url, RequestBody requestBody, final NetworkCallback callback) {

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                callback.onError(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    callback.onSuccess(myResponse);
                }
            }
        });
    }

    // Define a callback interface to handle network responses
    public interface NetworkCallback {
        void onSuccess(String response);
        void onError(Exception e);
    }
}
