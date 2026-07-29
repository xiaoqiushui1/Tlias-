package hhjvm.java.controller;

import hhjvm.java.pojo.Emp;
import hhjvm.java.pojo.Loginin;
import hhjvm.java.pojo.Result;
import hhjvm.java.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(("/login"))

public class logincontroller {
    @Autowired
    private EmpService empService;
    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("员工登录,参数{}",emp);
        Loginin loginin=empService.login(emp);
        if(loginin!=null){
        return Result.success(loginin);}
        return Result.error("用户名或密码错误");
    }

}
