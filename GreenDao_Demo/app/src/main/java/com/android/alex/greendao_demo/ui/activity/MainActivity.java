package com.android.alex.greendao_demo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.alex.greendao_demo.R;
import com.android.alex.greendao_demo.data.User;
import com.android.alex.greendao_demo.gen.UserDao;
import com.android.alex.greendao_demo.ui.App;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_update, R.id.btn_query})
    public void onViewClicked(View view) {
        UserDao userDao = App.getDaoSession().getUserDao();
        switch (view.getId()) {
            case R.id.btn_add:
                //增加数据
                for (int i = 0; i < 10; i++) {
                    User user = new User();
                    user.setName("司马" + i);
                    userDao.insertOrReplace(user);
                }
                break;
            case R.id.btn_delete:
                //删除数据
                userDao.deleteByKey((long) 1);
                break;
            case R.id.btn_update:
                User cusomter = new User();
                cusomter.setId((long) 1);
                cusomter.setName("疾风剑豪");

                User stundent = new User();
                stundent.setId((long) 10);
                stundent.setName("Tom");

                User users[] = new User[]{cusomter,stundent};
                //userDao.update(cusomter);
                userDao.updateInTx(users);
                break;
            case R.id.btn_query:
                User user = userDao.load((long) 1);
                String userInfo = user.toString();
                tvContent.setText(userInfo);

                /*List<User> userList = userDao.loadAll();
                //借助一个字符串缓冲区
                StringBuilder sb = new StringBuilder();
                //遍历循环获取所有内容
                for (int i = 0; i < userList.size(); i++) {
                    User user = userList.get(i);
                    String userInfo = user.toString();
                    sb.append(userInfo + "---");
                }
                //查询到的所有数据设置给TextView进行显示
                String queryAllInfo = sb.toString();
                tvContent.setText(queryAllInfo);*/
                break;
        }
    }
}
