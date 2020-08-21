package com.demo.ck.js_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private Button button;

    private BsoftJsInterface bsoftJsInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.webView);
        button = findViewById(R.id.button);
        initWeb();
        loadUrl();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.evaluateJavascript(bsoftJsInterface.seth5("button2222"), new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.i("ckd","onReceiveValue="+value);
                    }
                });
            }
        });
    }

    private void initWeb(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);//解决图片不显示
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.requestFocus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.d("ckd", cm.message() + " -- From line "
                        + cm.lineNumber() + " of "
                        + cm.sourceId() );
                return true;
            }
        });
        bsoftJsInterface = new BsoftJsInterface(this);
        webView.addJavascriptInterface(bsoftJsInterface, "bsoftJsInterface");
    }

    private void loadUrl(){
        webView.loadUrl("http://169.254.73.149:8000/");
    }
}
