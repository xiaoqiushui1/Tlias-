package hhjvm.java.service;

import hhjvm.java.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();
//根据部门id删除数据
    void deleteById(Integer id);
    /*
    *新增部门
     */
    void add(Dept dept);
    //根据id来查询部门数据
    Dept getById(Integer id);
//修改数据
    void update(Dept dept);
}
