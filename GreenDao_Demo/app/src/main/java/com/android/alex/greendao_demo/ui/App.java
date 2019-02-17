package com.android.alex.greendao_demo.ui;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.android.alex.greendao_demo.gen.DaoMaster;
import com.android.alex.greendao_demo.gen.DaoSession;

/**
 * @author Alex
 * @date 2019/2/14.
 * GitHub：https://github.com/wangshuaialex
 */
public class App extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        //参数1：上下文
        //参数2：数据库名称
        //参数3：游标工厂类
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "alex", null);
        //获取DataBase对象
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //创建DaoMaster对象，所需DateBase数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //创建DaoSession对象
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
