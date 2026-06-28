package com.tianming.controller;

import com.tianming.mapper.ClazzMapper;
import com.tianming.pojo.Clazz;
import com.tianming.pojo.ClazzQueryParam;
import com.tianming.pojo.PageResult;
import com.tianming.pojo.Result;
import com.tianming.service.ClazzService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {


    @Autowired
    private ClazzService classService;

    @GetMapping
    public Result selectclazz(ClazzQueryParam clazzQueryParam)
    {
        log.info("查询班级");
        PageResult<Clazz> Page=classService.getClass(clazzQueryParam);
        return Result.success(Page);
    }

    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable Integer id)
    {
        log.info("删除班级，{}",id);
        classService.deleteClazz(id);
        return Result.success();
    }

    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz)
    {
        log.info("增加班级，{}",clazz);
        classService.addClazz(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectClazzById(@PathVariable Integer id)
    {
        log.info("根据id来查询班级，{}",id);
        Clazz clazzById=classService.selectClazzById(id);
        return Result.success(clazzById);
    }

    @PutMapping
    public Result putClazz(@RequestBody Clazz clazz)
    {
        log.info("修改班级信息，{}",clazz);
        classService.putClazz(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result selectAllClazz()
    {
        List<Clazz> allClazz=classService.selectAllClazz();
        return Result.success(allClazz);
    }
}
