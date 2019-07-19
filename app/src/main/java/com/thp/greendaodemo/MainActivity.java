package com.thp.greendaodemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thp.greendaodemo.adapter.TableAdapter;
import com.thp.greendaodemo.bean.Student;
import com.thp.greendaodemo.greendao.gen.DaoSession;
import com.thp.greendaodemo.greendao.gen.StudentDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {
    RecyclerView recyclerView;
    EditText editText;
    Random mRandom = new Random();
    private TableAdapter adapter;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daoSession = ((MyApplication) getApplication()).getDaoSession();
        initView();
        initListener();
    }

    private void initListener() {
        findViewById(R.id.bt_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserData();
            }
        });
        findViewById(R.id.bt_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
            }
        });
        findViewById(R.id.bt_delete2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem();
                adapter.setNewData(queryAll());
            }
        });
        findViewById(R.id.bt_look).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryData("1年级");
            }
        });
        findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setId((long) 404);
                student.setName("大佬");
                updataData(student);
            }
        });
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.et_key);
        recyclerView = (RecyclerView) findViewById(R.id.table_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TableAdapter(R.layout.item_table);
        recyclerView.setAdapter(adapter);
        adapter.setNewData(queryAll());
    }

    void inserData() {

        for (int i = 0; i < 20; i++) {
            Student student = new Student();
            student.setStudentNo(mRandom.nextInt(100000));
            int age = mRandom.nextInt(10) + 10;
            student.setAge(age);
            student.setTelPhone(mRandom.nextInt(11) + "");
            String chineseName = "张三丰";
            student.setName(chineseName);
            if (i % 2 == 0) {
                student.setSex("男");
            } else {
                student.setSex("女");
            }
            student.setAddress("广东省佛山市顺德区");
            student.setGrade(String.valueOf(age % 10) + "年级");
            student.setSchoolName("清华大学");
            daoSession.insert(student);
        }
        adapter.setNewData(queryAll());
    }

    public List queryAll() {
        List<Student> students = daoSession.loadAll(Student.class);
        return students;
    }

    public void deleteAll() {
        daoSession.deleteAll(Student.class);
        adapter.setNewData(queryAll());
    }

    public void deleteData(Student s) {
        daoSession.delete(s);
        adapter.setNewData(queryAll());
    }

    public boolean deleteItem() {
        QueryBuilder<Student> where = daoSession.queryBuilder(Student.class).where(StudentDao.Properties.Sex.eq("男"));
        DeleteQuery<Student> deleteQuery = where.buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
        return false;
    }

    public void updataData(Student s) {
        daoSession.update(s);
    }

    public void queryData(String s) {
        List<Student> students = daoSession.queryRaw(Student.class, " where grade = ?", s);
        adapter.setNewData(students);
    }
}
