package hhjvm.java.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Student {
   private Integer id;
    private String name;
    private String no;//学号
    private String gender;
    private String phone;         // 手机号
    private String idCard;//身份证号
    private Integer isCollege;    // 是否院校
    private String address;       // 地址
    private Integer degree;       // 学历
    private String graduationDate;// 毕业日期
    private Integer clazzId;         // 班级ID
    private Integer violationCount; // 违纪次数
    private Integer violationScore; // 违纪扣分
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
    private String clazzName;     // 班级名称

}
