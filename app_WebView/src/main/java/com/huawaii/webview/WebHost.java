package com.huawaii.webview;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by huawaii on 2016/5/15.
 * 演示混淆打包出现的WebView与js调用失败
 */
public class WebHost {
    public Context mContext;

    public WebHost(Context context) {
        mContext = context;
    }

    public void call() {
        Toast.makeText(mContext, "fsafsd", 1000).show();
    }
}
