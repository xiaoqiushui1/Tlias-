package hhjvm.java.controller;

import hhjvm.java.anno.Log;
import hhjvm.java.pojo.Emp;
import hhjvm.java.pojo.EmpQueryParam;
import hhjvm.java.pojo.PageResult;
import hhjvm.java.pojo.Result;
import hhjvm.java.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/*
员工管理的Controller
 */
@Slf4j
@RequestMapping("/emps")//统一前缀请求
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
    //分页查询
    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize ,String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){//收到参数,@RequestParam注解可以设置默认值
    public Result page(EmpQueryParam empQueryParam){
//        log.info("分页查询,参数:{},{},{},{},{},{},",page,pageSize,name,gender,begin,end);
        log.info("分页查询,参数:{}"+empQueryParam);
        PageResult<Emp> pageResult=empService.page(empQueryParam);//条件分页查询
        return Result.success(pageResult);
    }
    /*
    新增员工，先接受请求参数用@Requestbody
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工,参数:{}"+emp);
        empService.save(emp);
        return Result.success();
    }
//    删除员工
    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工,参数:{}"+ids);
        empService.delete(ids);
        return Result.success();
    }
    //查询回显
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        log.info("查询员工回显,参数:{}"+id);
        Emp emp=empService.getInfo(id);
        return Result.success(emp);
    }
    //修改员工
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工,参数:{}"+emp);
        empService.update(emp);
        return Result.success();
    }
    //查询全部员工
    @GetMapping("/list")
    public Result list(){
        System.out.println("系统进入list路径");
        log.info("查询全部员工");
        List<Emp> list=empService.list();
        return Result.success(list);
    }

}
