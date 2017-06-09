package com.example.jack.properdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String userPath = "/mnt/sdcard/xgimi/";
    private static final String globalPath = "/mnt/sdcard/xgimi/EnvDefaultConfig.properties";
    private static final String MODULE_NAME = "H1";
    private static final String EnvDefaultConfig = "EnvDefaultConfig";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProperUtils.setEnvironment(getUserPath(globalPath,MODULE_NAME));

    }

    /**
     * 解决需要添加新机型时不用修改代码，只需要添加配置文件即可。
     *
     * @param path 传入默认的配置文件路径
     * @param module 传入当前机器的名
     * @return 产品路径或默认文件路径
     */
    private String getUserPath(String path, String module) {
        try {
            int lastPointIndex = path.lastIndexOf(".");
            Log.d("zhang","lastPointIndex="+lastPointIndex);

            String prx = path.substring(0, lastPointIndex);
            Log.d("zhang","prx="+prx);

            String suff = path.substring(lastPointIndex, path.length());
            Log.d("zhang","suff="+suff);

            String productPath = userPath + module + EnvDefaultConfig +suff;

            Log.d("zhang","productPath="+productPath);
            File file = new File(productPath);
            if (file == null || !file.isFile()) {
                return path;
            }

            return productPath;

        } catch (Exception e) {
            return path;
        }
    }
}
