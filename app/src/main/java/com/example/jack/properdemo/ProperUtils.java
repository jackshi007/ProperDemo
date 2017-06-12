package com.example.jack.properdemo;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;


public class ProperUtils {
    private static Properties modleProp;
    private static Properties model;


    public static void  setEnvironment(String file) {
        Properties mainprop = new OrderedProperties();
        try {
            //读取属性文件aproperties
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            mainprop.load(in);///加载属性列表
            modleProp = getPropertiesByType(mainprop,"setEnvironment");
            Log.d("getPropertiesByType","modleProp="+modleProp);
            model = getPropertiesByType(mainprop,"setCmdToNative");
            setEnvOrCmd(modleProp);
            setEnvOrCmd(model);
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 解决在同一个文件中存不同的类型的参数根据type类型去区分开来
     * @param mainProp
     * @param type
     * @return 返回同一type类型的系统参数
     */
    private static Properties getPropertiesByType(Properties mainProp, String type) {
        Properties prop = new OrderedProperties();
        boolean flag = false;
        for (String key : mainProp.stringPropertyNames()) {
            if (key.matches("\\[.*\\]")) {
                if (key.equals("[" + type + "]")) {
                    flag = true;
                } else {
                    flag = false;
                }
                continue;
            }
            if (flag) {
                prop.setProperty(key, mainProp.getProperty(key));
                Log.d("getPropertiesByType", "key=" + key+ "       value=" + mainProp.getProperty(key));
            }
        }
        return prop;
    }

    /**
     * 不同的类型的参数需要调用不同的方法去设置该参数。
     * @param properties
     */
    private static void setEnvOrCmd(Properties properties){
        if (properties == modleProp) {
            Iterator<String> it = properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                Log.d("zhang_setEnvironment", "key=" + key + "       value=" + properties.getProperty(key));
                //XgimiCommonManager.getInstance().setEnvironment(key, prop.getPropperty(key));
            }
        }else if (properties == model ){
            Iterator<String> it = properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                Log.d("zhang_setCmd", "key=" + key + "       value=" + properties.getProperty(key));
                //XgimiCommonManager.getInstance().setCmdToNative(key, prop.getPropperty(key));
            }
        }
    }
}
