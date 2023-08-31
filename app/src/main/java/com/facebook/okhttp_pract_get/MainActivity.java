package com.facebook.okhttp_pract_get;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
TextView textView;
Button buttonget,buttonpost;
    OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textview);
        buttonget=findViewById(R.id.getbutton);
        buttonpost=findViewById(R.id.postbutton);

        buttonget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getfunction();
            }
        });

        buttonpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postfunction();
            }
        });
    }

    private void postfunction() {
        client=new OkHttpClient.Builder()
                .addInterceptor(new ErrorInterceptor())
                .build();
        RequestBody requestBody=new FormBody.Builder()
                .add("Name","uzair")
                .build();

      Request request=new Request.Builder()
              .url("https://reqres.in/api/users")
              .post(requestBody)
              .build();
      client.newCall(request).enqueue(new Callback() {
          @Override
          public void onFailure(@NonNull Call call, @NonNull IOException e) {
              e.printStackTrace();
          }

          @Override
          public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
              if (response.isSuccessful()){
                  String myrequest=response.body().string();
                  MainActivity.this.runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          textView.setText(myrequest);
                      }
                  });
              }

          }
      });
    }
    private void getfunction(){
        client=new OkHttpClient();
        String url="https://dummyjson.com/products/5";

        Request request=new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String myresponse=response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(myresponse);
                        }
                    });
                }
            }
        });
    }
}