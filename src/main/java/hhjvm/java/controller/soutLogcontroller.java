package hhjvm.java.controller;

import com.github.pagehelper.PageHelper;
import hhjvm.java.pojo.OperateLog;
import hhjvm.java.pojo.PageResult;
import hhjvm.java.pojo.Pagesult1;
import hhjvm.java.pojo.Result;
import hhjvm.java.service.soutLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/log")
public class soutLogcontroller {
    @Autowired
   private soutLogService operateLogService;
    @GetMapping("/page")
    public Result page(Pagesult1 olog){
      log.info("分页查询员工日志数据,参数:{}"+olog);
        PageResult<OperateLog> page =operateLogService.page(olog);
        return Result.success(page);


    }




}
