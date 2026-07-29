package hhjvm.java.mapper;

import hhjvm.java.pojo.Student;
import hhjvm.java.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentMapper {
 List<Student> list(StudentQueryParam StudentQueryParam);
 void delete(String[] ids);


 @Insert("insert into student(id, name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score,create_time,update_time) " +
         "values(#{id}, #{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{violationCount}, #{violationScore},#{createTime},#{updateTime})")
    void save(Student student);
    @Select("select s.* from student s where id=#{id}")
    Student getInfo(Integer id);
     @Update("update student set name=#{name},no=#{no},gender=#{gender},phone=#{phone},id_card=#{idCard},is_college=#{isCollege},address=#{address},degree=#{degree},graduation_date=#{graduationDate},clazz_id=#{clazzId},violation_count=#{violationCount},violation_score=#{violationScore},update_time=#{updateTime} where id=#{id}")
    void update(Student student);
@Update("update student set violation_count=violation_count+1,violation_score=violation_score+#{score} where id=#{id}")
    void violation(Integer id, Integer score);
}
