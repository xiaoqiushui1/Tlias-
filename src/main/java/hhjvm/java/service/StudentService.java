package hhjvm.java.service;

import hhjvm.java.pojo.PageResult;
import hhjvm.java.pojo.Result;
import hhjvm.java.pojo.Student;
import hhjvm.java.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void deleteById(String [] ids);

    void save(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);
}
