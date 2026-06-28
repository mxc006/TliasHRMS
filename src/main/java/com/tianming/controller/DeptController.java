package com.tianming.controller;

import com.tianming.anno.Log;
import com.tianming.pojo.Dept;
import com.tianming.pojo.Result;
import com.tianming.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    //@RequestMapping(value="/depts" , method= RequestMethod.GET)
    @GetMapping
    @Log
    public Result list()
    {
        //System.out.println("查询全部部门的数据");
        log.info("查询全部部门数据");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }
    /*方式一
    @DeleteMapping("/depts")
    public Result delete(HttpServletRequest request)
    {
        int id=Integer.parseInt(request.getParameter("id"));
        System.out.println("根据id删除部门"+id);
        return Result.success();
    }*/
    /*方式二
    @DeleteMapping("/depts")
    public Result delete(@RequestParam(value="id",required = false) Integer  deptid)
    {
        System.out.println("根据id删除部门"+deptid);
        return Result.success();
    }
    */

    //方式三
    @Log
    @DeleteMapping
    public Result delete(Integer  id)
    {
        //System.out.println("根据id删除部门："+id);
        log.info("根据id删除部门：{}",id);
        deptService.delete(id);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept)
        {
            //System.out.println("添加部门："+dept);
            log.info("添加部门：{}",dept);
            deptService.add(dept);
            return Result.success();
        }
        @Log
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id)
    {
        //System.out.println("查询id为"+id+  "的部门");
        log.info("查询id为{}的部门",id);
        Dept dept=deptService.get(id);
        return Result.success(dept);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept)
    {
        //System.out.println("修改部门："+dept);
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}

