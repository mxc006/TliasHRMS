package com.tianming.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tianming.mapper.StudentMapper;
import com.tianming.pojo.PageResult;
import com.tianming.pojo.Student;
import com.tianming.pojo.StudentQueryParam;
import com.tianming.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentServiceIpml implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult<Student> getStudent(StudentQueryParam studentQueryParam) {
        //设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        List<Student> studentList = studentMapper.getStudent(studentQueryParam);
        Page<Student> p=(Page<Student>) studentList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteStudent(Integer[] ids) {
        log.info("删除学生信息");
        studentMapper.deleteStudent(ids);
    }

    @Override
    public void insert(Student student) {
        log.info("添加学生信息");
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getInfo(id);
    }

    @Override
    public void update(Student student) {
        log.info("修改学生信息");
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void studentScore(Integer id, Integer score) {
        log.info("违纪处理");
        studentMapper.studentScore(id,score);
    }
}
