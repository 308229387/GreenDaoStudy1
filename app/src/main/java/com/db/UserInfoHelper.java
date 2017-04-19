package com.db;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by songyongmeng on 2017/4/19.
 * 描    述：被Utils调用，使用级为2
 */

public class UserInfoHelper extends BaseDbHelper<User,Long> {
    public UserInfoHelper(AbstractDao dao) {
        super(dao);
    }
}
