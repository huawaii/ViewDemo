package com.huawaii.webview;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by huawaii on 2016/5/15.
 */
public class HttpThread  extends Thread {
    private String url;

    public HttpThread(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            URL httUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true); //不注释会出现 connection.getInputStream()的java.io.FileNotFoundException
            InputStream in = conn.getInputStream();

            File sdFile;
            File downLoaFile;
            FileOutputStream out = null;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                downLoaFile = Environment.getExternalStorageDirectory();
                sdFile = new File(downLoaFile, "test.apk");
                out = new FileOutputStream(sdFile);
            }

            byte[] b = new byte[6 * 1024];
            int len;
            while ((len = in.read(b)) != -1) {
                if (out != null) {
                    out.write(b, 0, len);
                }
            }

            //完成关闭流
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            System.out.println("<<<<<<<<<<<<<<<<" + "完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        super.run();
    }
}
