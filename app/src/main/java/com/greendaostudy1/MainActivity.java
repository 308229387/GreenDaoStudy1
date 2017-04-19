package com.greendaostudy1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dao_master.UserDao;
import com.db.DBUtil;
import com.db.User;
import com.db.UserInfoHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要看方法，看DEMO，写的很详细
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name_et;
    private EditText age_et;
    private ListView listView;

    private List<User> list;
    private UserInfoHelper mHelper;
    private Toast toast;
    private ArrayAdapter<String> adapter;
    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_et = (EditText) findViewById(R.id.et_name);
        age_et = (EditText) findViewById(R.id.et_age);
        listView = (ListView) findViewById(R.id.listview);

        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_delete_all).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_update_all).setOnClickListener(this);
        findViewById(R.id.btn_search_one).setOnClickListener(this);
        findViewById(R.id.btn_search_list).setOnClickListener(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);
        mHelper = DBUtil.getDriverHelper();
        update();
    }

    @Override
    public void onClick(View v) {
        String name = name_et.getText().toString().trim();
        String age = age_et.getText().toString().trim();

        User stu = new User();
        stu.setTest("我是TEST");
        stu.setUserName(name);
        stu.setPassWord(age);

        switch (v.getId()) {
            case R.id.btn_insert:
                mHelper.save(stu);
                break;
            case R.id.btn_delete:
                list = mHelper.queryAll();
                mHelper.delete(list.get(list.size() - 1));
                break;
            case R.id.btn_delete_all:
                mHelper.deleteAll();
                break;
            case R.id.btn_update:
                try {
                    List<User> s1 = mHelper.queryBuilder().where(UserDao.Properties.UserName.eq(name)).build().list();//
                    User t = s1.get(1);
                    t.setPassWord("123123123");
                    mHelper.saveOrUpdate(t);
                } catch (Exception e) {
                    showToast("没有可升级的目标");
                }

                break;
            case R.id.btn_update_all:
                List<User> s2 = mHelper.queryBuilder().where(UserDao.Properties.UserName.eq(name)).build().list();//
                for (User temp : s2)
                    temp.setPassWord("66666");
                mHelper.saveOrUpdate(s2);
                break;
            case R.id.btn_search_one:
                try {
                    User s = mHelper.queryBuilder().where(UserDao.Properties.UserName.eq(name)).build().unique();//只有一个时候用
                    showToast(s.getPassWord());
                } catch (Exception e) {
                    showToast("目标不存在或不唯一");
                }
                break;
            case R.id.btn_search_list:
                List<User> list = mHelper.queryBuilder().where(UserDao.Properties.UserName.eq(name)).build().list();//
                showToast(list.size() + "个合适的结果");
                break;

        }
        update();
    }

    public void showToast(String text) {
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        toast.setText(text);
        toast.show();
    }

    public void update() {
        List<User> list = mHelper.queryAll();
        datas.clear();
        for (int i = 0; i < list.size(); i++) {
            datas.add("名称：" + list.get(i).getUserName() + "                密码：" + list.get(i).getPassWord());
        }
        adapter.notifyDataSetChanged();
    }
}
