package com.example.amyas.customwidget.debug;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.amyas.customwidget.R;

public class WebViewTestActivity extends AppCompatActivity {
    public static final String TAG = "WebViewTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);

        WebView webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //        //支持插件
        //        webSettings.setPluginsEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        //优先使用缓存:
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        WebView.setWebContentsDebuggingEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(
                    WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webView.getSettings().setDomStorageEnabled(true);

        webView.getSettings().setAppCacheMaxSize(1024*1024*8);

        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();

        webView.getSettings().setAppCachePath(appCachePath);

        webView.getSettings().setAllowFileAccess(true);

        webView.getSettings().setAppCacheEnabled(true);

        webView.setWebChromeClient(new WebChromeClient());

//        webView.addJavascriptInterface(new WebAppInterface(), "Android");



        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。

        //        //不使用缓存:
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();    //表示等待证书响应
                // handler.cancel();      //表示挂起连接，为默认方式
                // handler.handleMessage(null);    //可做其他处理
            }

            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e(TAG, "onPageFinished: 开始加载");
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e(TAG, "onPageFinished: 结束加载");

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.e(TAG, "shouldOverrideUrlLoading: request" + request.getUrl());
                System.out.println(request.getUrl().toString());
                return super.shouldOverrideUrlLoading(view, request);
            }
        });


        //        String uri = Uri.encode("https://webview.cht.znrmny.com/couponList?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6IjE4MTcwODc4ODQ5IiwiYWNjb3VudF9pZCI6IjIzIn0.NTjMa-FT_GbeiQ4QRFzwHnFs6J3dULVIPV0Rwx_QPOE");
        //        URIBuilder ub = new URIBuilder("http://example.com/query");
        //        ub.addParameter("q", "random word £500 bank \$");
        //        String url = ub.toString();
        webView.loadUrl("https://webview.cht.znrmny.com/couponList?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6IjE4MTcwODc4ODQ5IiwiYWNjb3VudF9pZCI6IjIzIn0.NTjMa-FT_GbeiQ4QRFzwHnFs6J3dULVIPV0Rwx_QPOE");
    }

    class WebAppInterface {
    }
}
