package hhjvm.java.service.impl;

import hhjvm.java.exception.DeleteDeptHasEmpException;
import hhjvm.java.mapper.DeptMapper;
import hhjvm.java.mapper.EmpMapper;
import hhjvm.java.pojo.Dept;
import hhjvm.java.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
@Autowired
private DeptMapper deptMapper;
@Autowired
private EmpMapper empMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();//最后返回字段在控制层封装
    }

    @Override
    public void add(Dept dept) {
        //1.补全基础属性-creatime，updateTIime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper接口方法插入数据
        deptMapper.insert(dept);//将dept对象注入
    }
    /**
     *
     * 根据部门id删除部门
     */
    @Override
    public void deleteById(Integer id) {
        Long count = empMapper.countByDeptId(id);
        if (count > 0) {
            throw new DeleteDeptHasEmpException("对不起，当前部门下有员工，不能直接删除！");
        }
        deptMapper.deleteById(id);
    }
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);


    }
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
