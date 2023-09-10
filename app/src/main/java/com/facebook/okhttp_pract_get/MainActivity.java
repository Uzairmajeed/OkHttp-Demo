package com.facebook.okhttp_pract_get;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.FormBody;
import okhttp3.RequestBody;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button buttonget, buttonpost;
    NetworkOperations networkOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        buttonget = findViewById(R.id.getbutton);
        buttonpost = findViewById(R.id.postbutton);

        networkOperations = new NetworkOperations();

        buttonget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                networkOperations.performGetRequest("https://dummyjson.com/products/5", new NetworkOperations.NetworkCallback() {
                    @Override
                    public void onSuccess(String response) {
                        updateTextView(response);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });

        buttonpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody requestBody = new FormBody.Builder()
                        .add("Name", "uzair")
                        .build();


                networkOperations.performPostRequest("https://reqres.in/api/users", requestBody, new NetworkOperations.NetworkCallback() {
                    @Override
                    public void onSuccess(String response) {
                        updateTextView(response);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    private void updateTextView(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }
}
