package com.app;

import android.app.Application;

import com.db.DbCore;
import com.facebook.stetho.Stetho;

/**
 * Created by songyongmeng on 2017/4/19.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DbCore.init(this);//初始化
        DbCore.enableQueryBuilderLog();// 开启调试模式
        Stetho.initializeWithDefaults(this);
    }
}
