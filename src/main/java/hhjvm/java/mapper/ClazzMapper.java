package hhjvm.java.mapper;

import hhjvm.java.pojo.ClazzQueryParam;
import hhjvm.java.pojo.Clazz;
import hhjvm.java.pojo.ClazzQueryParam1;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> list(ClazzQueryParam classQueryParam);
@Delete("DELETE  from clazz where clazz.id=#{id}")
    void delete(Integer id);
@Insert("INSERT into clazz(name, room, begin_date, end_date, master_id, subject,create_time,update_time)values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void add(Clazz clazz);
@Select("select * from clazz where id=#{id}")
   List <Clazz> iddetect(Integer id);
@Update("UPDATE Clazz Set name=#{name},room=#{room},begin_date=#{beginDate}, end_date=#{endDate}, master_id=#{masterId}, subject=#{subject},update_time=#{updateTime} where id=#{id}")//#{}是注入进去，${}是拼接
    void  update(ClazzQueryParam1 clazz);

}
