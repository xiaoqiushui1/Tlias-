package hhjvm.java.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hhjvm.java.mapper.SoutMapper;
import hhjvm.java.pojo.Emp;
import hhjvm.java.pojo.OperateLog;
import hhjvm.java.pojo.PageResult;
import hhjvm.java.pojo.Pagesult1;
import hhjvm.java.service.soutLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class soutLogServiceImpl implements soutLogService {
    @Autowired
    private SoutMapper soutMapper;
    @Override
    public PageResult<OperateLog> page(Pagesult1 olog) {
        //1.调用mapper，查询总记录数
        PageHelper.startPage(olog.getPage(),olog.getPageSize());//对接下来的第一条sql查询拦截
        //2.调用mapper，查询结果列表
        List<OperateLog> list1=soutMapper.list(olog);
        //3.封装结果PageResult
        Page <OperateLog> p=(Page<OperateLog>) list1;
        return new PageResult<>(p.getPages(),p.getResult());//对应返回的json数据
    }
}
