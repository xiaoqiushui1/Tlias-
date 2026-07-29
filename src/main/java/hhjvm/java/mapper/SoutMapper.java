package hhjvm.java.mapper;

import hhjvm.java.pojo.OperateLog;
import hhjvm.java.pojo.Pagesult1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SoutMapper {
    List<OperateLog> list(Pagesult1 olog);
}
