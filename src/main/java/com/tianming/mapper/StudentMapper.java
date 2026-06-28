package com.tianming.mapper;

import com.tianming.pojo.Student;
import com.tianming.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {


    List<Student> getStudent(StudentQueryParam studentQueryParam);

    void deleteStudent(Integer[] ids);

    void insert(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void studentScore(Integer id, Integer score);

    List<Map<String, Object>> countStudentDegreeData();

    List<Map<String, Object>> masterlist();
}
