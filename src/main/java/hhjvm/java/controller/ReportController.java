package hhjvm.java.controller;

import hhjvm.java.pojo.ClassStudent;
import hhjvm.java.pojo.JobOptions;
import hhjvm.java.pojo.Result;
import hhjvm.java.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;
    //统计员工职位人数
    @GetMapping("/empJobData")
    Result EmpJobCount(){
        log.info("生成员工岗位统计数据");
        JobOptions jobOptions=reportService.getEmpJobDate();
        return Result.success(jobOptions);
    }
    //统计性别
    @GetMapping("/empGenderData")
    Result EmpGenderCount(){
        log.info("生成员工性别统计数据");
        List<Map<String,Object>> genderList=reportService.EmpCountgender();
        return Result.success(genderList);
    }
    //统计学生学历信息
    @GetMapping("/studentDegreeData")
    public Result StudentDegreeCount(){
        log.info("生成员工学历统计数据");
        List<Map<String,Object>> degreeList=reportService.EmpCountdegree();
        return Result.success(degreeList);
    }
    //统计班级人数
    @GetMapping("/studentCountData")
    public Result StudentCountData(){
        log.info("生成班级人数统计数据");
        ClassStudent clazzStudent=reportService.getstudnetDate();
        return Result.success(clazzStudent);
    }
}
