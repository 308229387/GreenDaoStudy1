package com.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import static android.R.attr.id;

/**
 * Created by songyongmeng on 2017/4/19.
 */
@Entity
public class User {
    @Id
    private Long Id;
    @Property(nameInDb = "username")
    private String userName;
    @Property(nameInDb = "password")
    private String passWord;
    @Property(nameInDb = "test")
    private String test;
    public String getTest() {
        return this.test;
    }
    public void setTest(String test) {
        this.test = test;
    }
    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    @Generated(hash = 1796654474)
    public User(Long Id, String userName, String passWord, String test) {
        this.Id = Id;
        this.userName = userName;
        this.passWord = passWord;
        this.test = test;
    }
    @Generated(hash = 586692638)
    public User() {
    }


}
