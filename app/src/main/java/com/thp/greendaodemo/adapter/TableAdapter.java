package com.thp.greendaodemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thp.greendaodemo.R;
import com.thp.greendaodemo.bean.Student;

/**
 * Created by thp on 2019/7/19
 */
public class TableAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {
    public TableAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Student item) {
        helper.setText(R.id.tv_id, item.getId() + "");
        helper.setText(R.id.tv_studentno, item.getStudentNo() + "");
        helper.setText(R.id.tv_age, item.getAge() + "");
        helper.setText(R.id.tv_telphone, item.getTelPhone() + "");
        helper.setText(R.id.tv_sex, item.getSex() + "");
        helper.setText(R.id.tv_name, item.getName() + "");
        helper.setText(R.id.tv_address, item.getAddress() + "");
        helper.setText(R.id.tv_schoolName, item.getSchoolName() + "");
        helper.setText(R.id.tv_grade, item.getGrade() + "");


    }
}
