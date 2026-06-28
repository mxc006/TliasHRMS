package com.tianming.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tianming.mapper.EmpExprMapper;
import com.tianming.mapper.EmpMapper;
import com.tianming.pojo.*;
import com.tianming.service.EmpLogService;
import com.tianming.service.EmpService;
import com.tianming.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
  /*  ---------------------原始分页--------------------------
  @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //调用Mapper查询总记录数
        long total = empMapper.count();
        //调用Mapper查询当前页数据
        List<Emp> rows = empMapper.list((page-1)*pageSize,pageSize);
        //封装PageResult对象并返回
        return new PageResult<Emp>(total,rows);
    }*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empqueryparam) {
        //1.设置分页参数
        PageHelper.startPage(empqueryparam.getPage(),empqueryparam.getPageSize());
        //2.查询当前页数据
        List<Emp> empList = empMapper.list(empqueryparam);
        //3.封装PageResult对象并返回
        Page<Emp> p=(Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());

    }

    @Transactional
    @Override
    public void insert(Emp emp) {
        try {
            //保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            //保存工作经历信息
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.insert(exprList);
            }
        }finally {
            //保存员工日志
            EmpLog emplog = new EmpLog(null,LocalDateTime.now(),"新增员工："+emp);
            empLogService.insertLog(emplog);
        }


    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {

        //删除员工基本信息
        empMapper.deleteByIds(ids);
        //删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1.修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        //2.修改员工工作经历信息
        //2.1 删除员工工作经历信息
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2.2 保存员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            //遍历集合获取，为empId赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insert(exprList);
        }
    }


    @Override
    public List<Emp> masterlist() {
        return empMapper.masterlist();

    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.根据用户名查询员工信息
        Emp e = empMapper.getByUsernameAndPassword(emp.getUsername(), emp.getPassword());
        //2.判断员工是否存在
        if(e != null) {
            log.info("员工登录成功：{}", e);
            //生成令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateToken(claims);

            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }
        return null;
    }
}
