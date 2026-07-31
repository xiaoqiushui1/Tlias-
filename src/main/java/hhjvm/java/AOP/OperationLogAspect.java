package hhjvm.java.AOP;
import hhjvm.java.mapper.OperateLogMapper;
import hhjvm.java.pojo.OperateLog;
import hhjvm.java.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;
@Aspect
@Slf4j
@Component
public class OperationLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(hhjvm.java.anno.Log)")//匹配所有使用Log注解(自定义)的方法
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();//记录开始时间
        // 执行目标方法
        Object result = joinPoint.proceed();
        // 计算耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        //构建日志实体
        OperateLog olog = new OperateLog();
// 1. 操作人ID ———— 从当前登录用户获取（需要你实现 getCurrentUserId() 方法）
       olog.setOperateEmpId(getCurrentUserId());
// 2. 操o作时间 ———— 记录操作发生的时刻
       olog.setOperateTime(LocalDateTime.now());
// 3. 类o名 ———— 获取被代理的目标类（如 DeptServiceImpl）
       olog.setClassName(joinPoint.getTarget().getClass().getName());
// 4. 方o法名 ———— 获取被调用的方法名（如 getById）
       olog.setMethodName(joinPoint.getSignature().getName());
// 5. 方o法参数 ———— 将参数数组转为字符串（如 [1]）
       olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));//alt+a可以列编辑
// 6. 返o回值 ———— 如果结果为空返回 "void"，否则返回结果的字符串表示
       olog.setReturnValue(result != null ? result.toString() : "void");
// 7. 耗o时（毫秒） ———— 方法执行消耗的时间
       olog.setCostTime(costTime);
//保存日志
        log.info("保存日志:{}",olog);
        operateLogMapper.insert(olog);
return  result;
    }
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}