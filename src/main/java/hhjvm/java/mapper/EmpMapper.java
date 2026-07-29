package hhjvm.java.mapper;

import hhjvm.java.pojo.Emp;
import hhjvm.java.pojo.EmpQueryParam;
import hhjvm.java.pojo.Loginin;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/*
 员工信息
  */
@Mapper
public interface EmpMapper {
    //分页 查询法一：
//    @Select("select count(*)  from emp e left join dept d on e.dept_id = d.id")
//    public Long count();
//    /*
//    查询总记录数
//     */
//    /*
//    分页查询
//     */
//    @Select("select e. * , d.name deptName from emp e left join dept d on e.dept_id = d.id order by entry_date desc " +
//            "limit #{start},#{pageSize}")
   // public List<Emp> List(Integer start,Integer pageSize);//转递参数,加名字即可
  //  法二：
    //@Select("select e. * , d.name deptName from emp e left join dept d on e.dept_id = d.id order by entry_date desc")
//   public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);//转递参数,加名字即可
//条件查询员工姓名
    List<Emp> list(EmpQueryParam empQueryParam);
    /**
     *新增员工基本信息
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")//因为员工还没插入数据库，所以数据库主键还不存在,用options获取到生成的id值，主键返回给emp对象之后再给empid赋值.
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id,create_time,update_time) " +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);
//根据id批量删除员工基本信息
    void delete(List<Integer> ids);

    Emp getById(Integer id);
    //根据id修改员工基本信息
    void updateById(Emp emp);
@Select("select * from emp ")
    List<Emp> alist();
//统计部门下是否有员工，报异常
    @Select(" SELECT COUNT(*) FROM emp WHERE dept_id = #{Id}")
    Long countByDeptId(Integer id);
@Select("SELECT id,username,name from emp where username=#{username} and password=#{password}")
    Emp login(Emp emp);
}
