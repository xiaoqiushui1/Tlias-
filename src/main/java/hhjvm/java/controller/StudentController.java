package hhjvm.java.controller;

import hhjvm.java.anno.Log;
import hhjvm.java.pojo.PageResult;
import hhjvm.java.pojo.Result;
import hhjvm.java.pojo.Student;
import hhjvm.java.pojo.StudentQueryParam;
import hhjvm.java.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationSupport;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService StudentService;
    /**
     * 分页查询
     * @param studentQueryParam
     * @return
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询{}参数"+studentQueryParam);
        PageResult<Student> pageResult= StudentService.page(studentQueryParam);
        return Result.success(pageResult);
    }
    //批量删除学员信息
    @Log
    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable String  ids){//路径 /1,2,3 本质是单个字符串："1,2,3"
        //@PathVariable String[] ids 这种写法，SpringMVC 不会自动按逗号切割成字符串数组，只会把整个 1,2,3 当成数组唯一一个元素，而且极易出现类型转换异常。
        log.info("批量删除员工信息{}参数" ,ids);
        // 手动按逗号分割为字符串数组，再转数字数组
        String[] idStrArr = ids.split(",");//String ids = "1,2,3";
        //String[] arr = ids.split(",");
        StudentService.deleteById(idStrArr);
        return Result.success();

        }

//添加学生
    @Log
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增员工,参数:{}" + student);
        StudentService.save(student);
        return Result.success();
    }
    //根据ID查询学生
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("查询员工回显,参数:{}" + id);
        Student student = StudentService.getInfo(id);
        return Result.success(student);
    }
    //修改学员
    @Log
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改员工,参数:{}" + student);
        StudentService.update(student);
        return Result.success();
    }
    //违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违纪处理,参数:{}" + id);
        StudentService.violation(id, score);
        return Result.success();
    }

}
