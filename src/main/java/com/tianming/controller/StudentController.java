package com.tianming.controller;

import com.tianming.pojo.PageResult;
import com.tianming.pojo.Result;
import com.tianming.pojo.Student;
import com.tianming.pojo.StudentQueryParam;
import com.tianming.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result PageselectStudent(StudentQueryParam studentQueryParam)
    {
        log.info("查询学生信息");
        PageResult<Student> Page=studentService.getStudent(studentQueryParam);
        return Result.success(Page);
    }

    @DeleteMapping("/{ids}")
    public Result deleteStudent(@PathVariable Integer[] ids)
    {
        log.info("删除学生信息");
        studentService.deleteStudent(ids);
        return Result.success();
    }

    @PostMapping
    public Result insertStudent(@RequestBody Student student)
        {
            log.info("添加学生信息");
            studentService.insert(student);
            return Result.success();
        }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id)
    {
        log.info("查询学生信息");
        Student student=studentService.getInfo(id);
        return Result.success(student);
    }

    @PutMapping
    public Result updateStudent(@RequestBody Student student)
    {
        log.info("修改学生信息");
        studentService.update(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result studentScore(@PathVariable Integer id, @PathVariable Integer score)
        {
            log.info("违纪处理");
            studentService.studentScore(id,score);
            return Result.success();
        }
}
