package com.tianming.service;

import com.tianming.pojo.Emp;
import com.tianming.pojo.EmpQueryParam;
import com.tianming.pojo.LoginInfo;
import com.tianming.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empqueryparam);

    void insert(Emp emp);

    void delete(List<Integer> id);

    /*根据员工id查询信息*/
    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> masterlist();

    LoginInfo login(Emp emp);
}
