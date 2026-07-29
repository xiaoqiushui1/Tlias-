package hhjvm.java.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hhjvm.java.mapper.StudentMapper;
import hhjvm.java.pojo.*;
import hhjvm.java.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper StudentMapper;
    @Override
    public PageResult<Student> page(StudentQueryParam StudentQueryParam) {
        PageHelper.startPage(StudentQueryParam.getPage(),StudentQueryParam.getPageSize());
        //对接下来的第一条sql查询拦截,根据封装得到的页数和总页码进行分页查询（已经计算过了（page-1）*pagesize）
        //查询结果
        List<Student> studentList=StudentMapper.list(StudentQueryParam);
        Page<Student> p=(Page<Student>) studentList;//强转集合，Page底层继承了ArrayList类
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteById(String[] ids) {
        StudentMapper.delete(ids);

    }
//新增员工，由于违纪是必须的所以赋初值就行，刚创建肯定没有违纪
    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount(0);
        student.setViolationScore(0);
        StudentMapper.save(student);
    }

    @Override
    public Student getInfo(Integer id) {
        Student student1=StudentMapper.getInfo(id);
        return student1;
    }
    //修改学员
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        StudentMapper.update(student);
    }
    //违纪处理


    @Override
    public void violation(Integer id, Integer score) {
        StudentMapper.violation(id,score);
    }
}
