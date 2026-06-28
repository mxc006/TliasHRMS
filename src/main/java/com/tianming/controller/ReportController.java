package com.tianming.controller;

import com.tianming.pojo.Result;
import com.tianming.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData()
    {
        log.info("生成员工职业表");
        return Result.success(reportService.countEmpJobData());
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData()
    {
        log.info("生成员工性别统计表");
        return Result.success(reportService.countEmpGenderData());
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeDate()
    {
        log.info("生成学生学历统计表");
        return Result.success(reportService.countStudentDegreeData());
    }
    @GetMapping("/studentCountData")
    public Result getStudentCountData()
    {
        log.info("生成学生学历统计表");
        return Result.success(reportService.masterlist());
    }
}
