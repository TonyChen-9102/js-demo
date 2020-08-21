package com.demo.ck.js_demo;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class BsoftJsInterface {
    private Context context;

    public BsoftJsInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public String setAndroid(String msg) {
        Log.i("ckd","msg="+msg);
        return "hahah";
    }

    //********************** Js - Android ******************
    public String seth5(String msg) {
        return "javascript:seth5(\"" + msg + "\")";
    }
}
