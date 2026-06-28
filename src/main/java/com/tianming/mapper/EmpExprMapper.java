package com.tianming.mapper;

import com.tianming.pojo.Emp;
import com.tianming.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void insert(List<EmpExpr> exprList);

    void deleteByEmpIds(List<Integer> empIds);
}
