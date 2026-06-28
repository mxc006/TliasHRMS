package com.tianming.controller;

import com.tianming.mapper.EmpExprMapper;
import com.tianming.pojo.Emp;
import com.tianming.pojo.EmpQueryParam;
import com.tianming.pojo.PageResult;
import com.tianming.pojo.Result;
import com.tianming.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result list(EmpQueryParam empqueryparam)
    {
        log.info("查询员工列表，{},empqueryparam");
        PageResult<Emp> pageResult=empService.page(empqueryparam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp)
    {
        log.info("添加员工，{}",emp);
        empService.insert(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids)
    {
        log.info("删除员工，id：{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id)
    {
        log.info("查询员工信息，id：{}",id);
        Emp emp=empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp)
    {
        log.info("更新员工信息，{}",emp);
        empService.update(emp);
        return Result.success();
    }
    @GetMapping("/list")
    public Result list()
    {
        log.info("查询员工列表");
        List<Emp> empList=empService.masterlist();
        return Result.success(empList);
    }
}
