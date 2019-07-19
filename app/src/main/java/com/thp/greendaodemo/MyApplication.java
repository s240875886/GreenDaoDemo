package com.thp.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.thp.greendaodemo.greendao.gen.DaoMaster;
import com.thp.greendaodemo.greendao.gen.DaoSession;

/**
 * Created by thp on 2019/7/19
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "demo.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
