package com.tianming.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tianming.mapper.ClazzMapper;
import com.tianming.pojo.Clazz;
import com.tianming.pojo.ClazzQueryParam;
import com.tianming.pojo.PageResult;
import com.tianming.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public PageResult<Clazz> getClass(ClazzQueryParam clazzQueryParam) {
        //设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        //获取数据
        List<Clazz> clazzList = clazzMapper.getClass(clazzQueryParam);
        Page<Clazz> p=(Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteClazz(Integer id) {
        clazzMapper.deleteClazz(id);

    }

    @Override
    public void addClazz(Clazz clazz) {
        //添加
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);
    }

    @Override
    public Clazz selectClazzById(Integer id) {
        return clazzMapper.selectClazzById(id);
    }

    @Override
    public void putClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.putClazz(clazz);
    }

    @Override
    public List<Clazz> selectAllClazz() {
        List<Clazz> clazzList = clazzMapper.selectAllClazz();
        return clazzList;
    }
}
