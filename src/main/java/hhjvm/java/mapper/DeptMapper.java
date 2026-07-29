package hhjvm.java.mapper;


import hhjvm.java.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //查询所有部门数据
    @Select("select id, name, create_time , update_time from dept order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept where id =#{id}")
    void deleteById(Integer id);
@Insert("insert into dept(name,create_time,update_time)values (#{name},#{createTime},#{updateTime})")//驼峰命名
    void insert(Dept dept);
/**
 *根据id查询部门数据
 */
    @Select("select id,name,dept.create_time,dept.update_time from dept where id=#{id}")
    Dept getById(Integer id);
    /**
     * 修改数据
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
void update(Dept dept);

}