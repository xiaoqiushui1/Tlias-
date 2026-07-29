package hhjvm.java.exception;

import hhjvm.java.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice//全局异常处理器
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("服务器发生异常:{}", e);
        return Result.error("服务器发生异常");
    }
@ExceptionHandler
public  Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出现异常", e);

        String message = e.getMessage();

        int i = message.indexOf("Duplicate entry");

        String errMsg = message.substring(i);

        String[] arr = errMsg.split(" ");

        return Result.error(arr[2] + " 已存在");

}
    // 3. 新增：删除部门存在员工自定义异常处理器（本次需求）
    @ExceptionHandler(DeleteDeptHasEmpException.class)
    public Result handleDeleteDeptHasEmpException(DeleteDeptHasEmpException e) {
        log.warn("删除部门校验异常：{}", e.getMessage());
        return Result.error(e.getMessage());
    }

}
