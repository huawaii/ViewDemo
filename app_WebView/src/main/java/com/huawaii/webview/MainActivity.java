package com.huawaii.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private TextView mTitleView;
    private Button mRefreshButton;
    private String mUrl = "https://github.com/huawaii/SystemUIDemo/blob/master/app_DesignPatterns/src/main/java/com/huawaii/designpatterns/MainActivity.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleView = (TextView) findViewById(R.id.title);
        mRefreshButton = (Button) findViewById(R.id.resh);

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.loadUrl(mUrl);


        //webView与js调用
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.addJavascriptInterface(new WebHost(this),"js");

        initView();
    }

    private void initView() {
        //设置标题
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitleView.setText(title);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            //让它在自己的activity中打开网页，
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            //当没有网络，或者是连接错误的时候使用
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request,
                                        WebResourceError error) {
                super.onReceivedError(view, request, error);
                //view.loadUrl("file:///android_asset/error.html");//引人一个asset下的一个加载错误页面
                //也可是使用自定义的控件去显示
                //mWebView.setVisibility(View.GONE);
                //提示控件.setVisibility(View.VISIBLE);
            }
        });

        //下载文件
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                        String mimetype, long contentLength) {
                // 开启一个线程去下载对象
                System.out.println("<<<<<<<<<<<<<<<<" + url);
                if (url.endsWith(".apk")) {
                    //自己去下载
                    //new HttpThread(url).start();
                    //通过系统下载
                    Uri uri =Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            }
        });

        //网页刷新
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.reload();
            }
        });
    }

}
