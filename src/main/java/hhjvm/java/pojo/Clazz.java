package hhjvm.java.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {

    // 班级ID
    private Integer id;

    // 班级名称
    private String name;

    // 班级教室
    private String room;

    // 开课时间
    private LocalDate beginDate;

    // 结课时间
    private LocalDate endDate;

    // 班主任ID
    private Integer masterId;
    //学科
    private Integer subject;

    // 班主任姓名
    private String masterName;
    // 创建时间
    private LocalDateTime createTime;//(这是后端数据库创建的那一刻记录时间，在sql上自己可以记录的，所以前端不用传)
    // 更新时间
    private LocalDateTime updateTime;//(这是后端数据库创建的那一刻记录时间，在sql上自己可以记录的，所以前端不用传)
    // 状态（未开班、已开班、已结课）
    private String status;
}