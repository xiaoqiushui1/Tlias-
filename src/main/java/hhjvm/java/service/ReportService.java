package hhjvm.java.service;

import hhjvm.java.pojo.ClassStudent;
import hhjvm.java.pojo.JobOptions;

import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Map<String, Object>> EmpCountgender();
    JobOptions getEmpJobDate();

    List<Map<String, Object>> EmpCountdegree();

    ClassStudent getstudnetDate();
}
