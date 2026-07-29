package hhjvm.java.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hhjvm.java.mapper.ClazzMapper;
import hhjvm.java.pojo.ClazzQueryParam;
import hhjvm.java.pojo.Clazz;
import hhjvm.java.pojo.ClazzQueryParam1;
import hhjvm.java.pojo.PageResult;
import hhjvm.java.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzsServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    //分页查询
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        //对接下来的第一条sql查询拦截,根据封装得到的页数和总页码进行分页查询（已经计算过了（page-1）*pagesize）
        //查询结果
        List<Clazz> clazzList=clazzMapper.list(clazzQueryParam);
        //封装结果
        Page<Clazz> p=(Page<Clazz>) clazzList;//强转集合，Page底层继承了ArrayList类
      return new PageResult<>(p.getTotal(),p.getResult());
    }

//根据id删除班级信息
    @Override
    public void delete(Integer id) {
        clazzMapper.delete(id);
    }
//添加班级信息
    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }
    //根据id查询班级信息
    @Override
    public List<Clazz> iddetect(Integer id) {
       List<Clazz> clazzList=clazzMapper.iddetect(id);
       return clazzList;
    }
    //修改班级信息
    @Override
    public void update(ClazzQueryParam1 clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }
    //查询所有班级信息
    @Override
    public List<Clazz> list(ClazzQueryParam clazzQueryParam) {
        List<Clazz> clazzList=clazzMapper.list(clazzQueryParam);
        return clazzList;
    }

}
