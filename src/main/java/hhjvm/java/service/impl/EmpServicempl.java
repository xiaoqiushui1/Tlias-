package hhjvm.java.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hhjvm.java.mapper.EmpExpMapper;
import hhjvm.java.mapper.EmpMapper;
import hhjvm.java.pojo.*;
import hhjvm.java.service.EmpLogService;
import hhjvm.java.service.EmpService;
import hhjvm.java.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.jmx.export.metadata.ManagedOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service//必须要用service要不Springboot找不到这个类
public class EmpServicempl implements EmpService {
    @Override
    public List<Emp> list() {
        List<Emp> empList= empMapper.alist();


        return empList ;
    }

    @Autowired
private EmpMapper empMapper;
    @Autowired
    private EmpExpMapper empExpMapper;
    @Autowired
    private EmpLogService  emplogService;
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1.调用mapper，查询总记录数
//        Long total= empMapper.count();
//
//        //2.调用mapper，查询结果列表
//        Integer start=(page-1)*pageSize;//计算起始索引，由于查询语句需要起始索引，传进来的为页码。
//        List<Emp> rows=empMapper.List(start,pageSize);//
//        //3.封装结果PageResult
//        return new PageResult<Emp>(total,rows);//传参值
//    }
    //利用PageHelper分页插件
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //1.调用mapper，查询总记录数
//         PageHelper.startPage(page,pageSize);//对接下来的第一条sql查询拦截
//        //2.调用mapper，查询结果列表
//         List<Emp> empLIst= empMapper.list(name,gender,begin,end);//已经进行分页了
//        //3.封装结果PageResult
//        Page<Emp> p=(Page<Emp>) empLIst;//强转集合，Page底层继承了ArrayList类
//   return new PageResult<>(p.getTotal(),p.getResult());

//    }
@Override
public PageResult<Emp> page(EmpQueryParam empQueryParam) {
    //1.调用mapper，查询总记录数
    PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());//对接下来的第一条sql查询拦截
    //2.调用mapper，查询结果列表
    List<Emp> empLIst= empMapper.list(empQueryParam);//已经进行分页了
    //3.封装结果PageResult
    Page<Emp> p=(Page<Emp>) empLIst;//强转集合，Page底层继承了ArrayList类
    return new PageResult<Emp>(p.getTotal(),p.getResult());
}

    @Override
    @Transactional(rollbackFor = Exception.class)//建立事务，保证数据一致性，同步性
    public void save(Emp emp) {
        try {
            //1.保存员工信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);//员工基本信息已经入表，这时候就有了id值了
            //2.保存员工经历
            List<EmpExpr> experList =emp.getExprList();
            if(!CollectionUtils.isEmpty(experList)){//判断是否为空
                //遍历集合，为empId赋值
                for (EmpExpr empExpr : experList){
                    empExpr.setEmpId(emp.getId());//为员工经历的empId赋值,便于知道知道那个员工的经历
                }
                   empExpMapper.insertBatch(experList);
            }
        } finally {
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            emplogService.insertLog(empLog);//开启两个事务
        }
    }
    @Transactional(rollbackFor = {Exception.class})//添加事务，保证数据一致性，同步性
    @Override
    public void delete(List<Integer> ids) {
        //1.删除员工基本信息
        empMapper.delete(ids);
        //2.删除员工经历信息
        empExpMapper.delectByEmpId(ids);

    }
//根据id查询员工信息和经历信息
    @Override
    public Emp getInfo(Integer id) {
    return empMapper.getById(id);
    }
@Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
    //1.根据ID修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);//和添加不同，因为添加的时候没有id值,只有员工表添加后才有id值.
    //2.先根据ID修改的工作经历
     //2.1 先根据员工ID删除员工原有的 工作经历
        empExpMapper.delectByEmpId(Arrays.asList(emp.getId()));
     //2.2再添加新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExpMapper.insertBatch(exprList);
        }
    }
    //登录功能
    @Override
    public Loginin login(Emp emp) {
    //调接口
       Emp e =empMapper.login(emp);
    //是否存在员工信息
        if (e!=null) {
            log.info("员工登录成功,员工信息{}",e);
            //生成jwt令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt= JwtUtils.generateJwt(claims);
        return new Loginin(e.getId(),e.getUsername(),e.getName(),jwt);
        }
        //不存在返回 null
    return null ;
    }
}
