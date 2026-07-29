package hhjvm.java.service;

import hhjvm.java.pojo.OperateLog;
import hhjvm.java.pojo.PageResult;
import hhjvm.java.pojo.Pagesult1;

public interface soutLogService {
    PageResult<OperateLog> page(Pagesult1 olog);
}
