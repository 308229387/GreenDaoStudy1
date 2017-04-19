package com.db;

import com.dao_master.UserDao;

/**
 * Created by songyongmeng on 2017/4/19.
 * 描    述：直接调用的，使用级为1；
 */

public class DBUtil {
    private static UserInfoHelper userInfoHelper;

    private static UserDao getDriverDao() {
        return DbCore.getDaoSession().getUserDao();
    }

    public static UserInfoHelper getDriverHelper() {
        if (userInfoHelper == null) {
            userInfoHelper = new UserInfoHelper(getDriverDao());
        }
        return userInfoHelper;
    }

    private static UserDao getUserInfoDao() {
        return DbCore.getDaoSession().getUserDao();
    }

    private static UserInfoHelper getUserInfoHelper() {
        if (userInfoHelper == null) {
            userInfoHelper = new UserInfoHelper(getUserInfoDao());
        }
        return userInfoHelper;
    }
}
