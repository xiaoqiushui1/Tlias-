package hhjvm.java.service;

import hhjvm.java.pojo.Emp;
import hhjvm.java.pojo.EmpQueryParam;
import hhjvm.java.pojo.Loginin;
import hhjvm.java.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
//    PageResult<Emp> page(Integer page, Integer pageSize, String name,Integer gender,LocalDate begin,LocalDate end);

    PageResult<Emp> page(EmpQueryParam empQueryParam);
//新增员工
    void save(Emp emp);
//删除员工
    void delete(List<Integer> ids);
//查询员工回显
    Emp getInfo(Integer id);
//查询回显之后修改员工
    void update(Emp emp);

    List<Emp> list();

    Loginin login(Emp emp);
}
