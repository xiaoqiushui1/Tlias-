package hhjvm.java.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface ReportMapper {
    @MapKey("job")
     List<Map<String, Object>> EmpCountjobnum();
    @MapKey("gender")
    List<Map<String, Object>>EmpCountgender();
    @MapKey("degree")
    List<Map<String, Object>> EmpCountdegree();
    @MapKey("clazzId")
    List<Map<String, Object>> getstudnetDate();
}
