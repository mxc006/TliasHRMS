package com.tianming.mapper;

import com.tianming.pojo.Emp;
import com.tianming.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    //@Select("select e.*,d.name deptName from emp e left join dept d on d.id = e.dept_id order by e.update_time desc")
    public List<Emp> list(EmpQueryParam empqueryparam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("insert into emp(username,password,name,gender,phone,job,salary,image,entry_date,dept_id,update_time) " +
            "values(#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{updateTime})")
    void insert(Emp emp);


    void deleteByIds(List<Integer> ids);

    /*根据id查询员工信息*/
    Emp getInfo(Integer id);
    /*修改员工信息*/
    void update(Emp emp);

    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    List<Map<String, Object>> countEmpGenderData();

    List<Emp> masterlist();

    Emp getByUsernameAndPassword(String username, String password);
}
