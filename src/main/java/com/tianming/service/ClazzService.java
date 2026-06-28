package com.tianming.service;


import com.tianming.pojo.Clazz;
import com.tianming.pojo.ClazzQueryParam;
import com.tianming.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    PageResult<Clazz> getClass(ClazzQueryParam clazzQueryParam);

    void deleteClazz(Integer id);

    void addClazz(Clazz clazz);

    Clazz selectClazzById(Integer id);

    void putClazz(Clazz clazz);

    List<Clazz> selectAllClazz();
}
