package com.tianming.service;

import com.tianming.pojo.PageResult;
import com.tianming.pojo.Student;
import com.tianming.pojo.StudentQueryParam;

public interface StudentService {
    PageResult<Student> getStudent(StudentQueryParam studentQueryParam);

    void deleteStudent(Integer[] ids);

    void insert(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void studentScore(Integer id, Integer score);
}
