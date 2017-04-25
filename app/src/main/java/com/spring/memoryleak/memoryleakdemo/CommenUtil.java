package com.spring.memoryleak.memoryleakdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Spring Email:spring0101@foxmail.com
 * @ClassName:CommenUtil
 * @date: 2017-4-25 14:00
 * @Description:
 */
public class CommenUtil {

    private CommenUtil() {
    }

    private static Context mContext;
    private static CommenUtil mInstance;

    public static CommenUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new CommenUtil();
            mContext = context;
        }
        return mInstance;
    }

    public void sayHello() {
        Log.i("CommenUtil", "hello CommenUtil");
    }
}