package com.tianming.service.impl;

import com.tianming.mapper.ClazzMapper;
import com.tianming.mapper.EmpMapper;
import com.tianming.mapper.StudentMapper;
import com.tianming.pojo.ClazzOption;
import com.tianming.pojo.JobOption;
import com.tianming.pojo.Student;
import com.tianming.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public Object countEmpJobData() {
        //调用Mapper接口，获取数据
        List<Map<String,Object>> list= empMapper.countEmpJobData();
        //封装数据
        List<Object> dataList=list.stream().map(m->m.get("pos")).toList();
        List<Object> jobList=list.stream().map(m->m.get("total")).toList();
        return new JobOption(dataList,jobList);
    }

    @Override
    public Object countEmpGenderData() {
        List<Map<String,Object>> genderList= empMapper.countEmpGenderData();
        return genderList;
    }

    @Override
    public Object countStudentDegreeData() {
        List<Map<String,Object>> degreeList= studentMapper.countStudentDegreeData();
        return degreeList;
    }

    @Override
    public Object masterlist() {
        List<Map<String,Object>> list= studentMapper.masterlist();
        List< Object> clazzList=list.stream().map(m->m.get("clazzName")).toList();
        List< Object> dataList=list.stream().map(m->m.get("total")).toList();
        return new ClazzOption(clazzList,dataList);
    }
}
