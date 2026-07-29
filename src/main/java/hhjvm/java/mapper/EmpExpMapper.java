package hhjvm.java.mapper;

import hhjvm.java.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/*
员工工作经历
 */

@Mapper

public interface EmpExpMapper {
    void insertBatch(List<EmpExpr> experList);

    void delectByEmpId(List<Integer> empIds);
}
