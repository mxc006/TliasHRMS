package com.tianming.service;

import com.tianming.pojo.Dept;

import java.util.List;

public interface DeptService {
//查询所有部门
    List<Dept> findAll();

    void delete(Integer id);

    void add(Dept dept);

    Dept get(Integer id);

    void update(Dept dept);
}
