package hhjvm.java.service.impl;

import hhjvm.java.mapper.ReportMapper;
import hhjvm.java.pojo.ClassStudent;
import hhjvm.java.pojo.JobOptions;
import hhjvm.java.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportMapper reportMapper;
//统计员工职位人数
    @Override
    public JobOptions getEmpJobDate() {
        List<Map<String, Object>> List=reportMapper.EmpCountjobnum();
        List<Object> jobList = new ArrayList<>();//等于List<Object> jobList =
        //list.stream().map(dataMap -> dataMap.get("pos")).toList();
        for (Map<String,Object> dataMap : List) {
            jobList.add(dataMap.get("pos"));
        }
        //等于List<Object> jobList =
        //        //list.stream().map(dataMap -> dataMap.get("num")).toList();
        List<Object> dataList = new ArrayList<>();
        for (Map<String,Object> dataMap : List) {
            dataList.add(dataMap.get("num"));
        }
        return new JobOptions(jobList,dataList);
    }
    //统计员工性别人数
    @Override
    public List<Map<String, Object>> EmpCountgender() {
        List<Map<String, Object>> List= reportMapper.EmpCountgender();
        return List;
    }
    //统计学生学历信息

    @Override
    public List<Map<String, Object>> EmpCountdegree() {
        List<Map<String, Object>> List= reportMapper.EmpCountdegree();
        return List ;
    }
    //统计学生班级信息

    @Override
    public ClassStudent getstudnetDate() {
     List<Map<String, Object>> List =reportMapper.getstudnetDate();
        List<Object> clazzList = new ArrayList<>();//等于List<Object> jobList =
        //list.stream().map(dataMap -> dataMap.get("pos")).toList();
        for (Map<String,Object> dataMap : List) {
            clazzList.add(dataMap.get("Name"));
        }
        //等于List<Object> jobList =
        //        //list.stream().map(dataMap -> dataMap.get("num")).toList();
        List<Object> dataList = new ArrayList<>();
        for (Map<String,Object> dataMap : List) {
            dataList.add(dataMap.get("Num"));
        }


        return new ClassStudent(clazzList,dataList);
    }
}
