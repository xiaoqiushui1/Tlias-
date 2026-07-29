package hhjvm.java.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentQueryParam {
    private String name;
    private Integer degree ;
    private Integer clazzId;
    private Integer page;
    private Integer pageSize;

}
