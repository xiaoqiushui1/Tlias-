package hhjvm.java.controller;

import hhjvm.java.anno.Log;
import hhjvm.java.pojo.*;
import hhjvm.java.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService ClazzService;
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询{}参数"+clazzQueryParam);
        PageResult<Clazz> pageResult= ClazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }
    @Log
@DeleteMapping("/{id}")//应为这个删除是单个删除所以不用集合来接，在路径里接受这个整数就行。用@PathVariable
    public Result Clazzdelete(@PathVariable Integer id){
        log.info("删除员工,参数:{}"+id);
        ClazzService.delete(id);
        return Result.success();
}
//添加班级信息
    @Log
@PostMapping
    public Result Clazzadd(@RequestBody Clazz clazz){
        log.info("新增员工,参数:{}"+clazz);
        ClazzService.add(clazz);
        return Result.success();
}//用RequestBody接受参数,将json数据转为对象
 @GetMapping("/{id}")   //根据ID查询
 public Result Clazziddetect(@PathVariable Integer id){
     List<Clazz> clazzList=ClazzService.iddetect(id);
        return Result.success(clazzList);
 }
 @Log
@PutMapping
    public Result Clazzupdate(@RequestBody ClazzQueryParam1 Clazz){
        log.info("更新员工,参数:{}"+Clazz);
        ClazzService.update(Clazz);
        return Result.success();
}
@GetMapping("/list")
    public Result Clazzlist(ClazzQueryParam clazzQueryParam){
        log.info("查询员工列表,参数:{}"+clazzQueryParam);
        List<Clazz> clazzList=ClazzService.list(clazzQueryParam);
        return Result.success(clazzList);
}


}
