package com.example.stackoverflow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stackoverflow.services.DispatcherService;

public class PostViewActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        //get search term saved in shared prefs
        SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);
        String postUrl = prefs.getString(MainActivity.KEY_POST_URL, "https://stackoverflow.com/");

        webView=findViewById(R.id.webviewid);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(postUrl);
    }

    public void gohome(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Save As last Activity
        DispatcherService.saveActivity(this);
    }
}