package com.tianming.mapper;

import com.tianming.pojo.Clazz;
import com.tianming.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {

    List<Clazz> getClass(ClazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id=#{id}")
    void deleteClazz(Integer id);

    void addClazz(Clazz clazz);

    Clazz selectClazzById(Integer id);

    void putClazz(Clazz clazz);

    List<Clazz> selectAllClazz();

}
