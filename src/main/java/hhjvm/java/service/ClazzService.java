package hhjvm.java.service;

import hhjvm.java.pojo.ClazzQueryParam;
import hhjvm.java.pojo.Clazz;
import hhjvm.java.pojo.ClazzQueryParam1;
import hhjvm.java.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);


    void delete(Integer id);

    void add(Clazz clazzQuery1);

    List<Clazz> iddetect(Integer id);

     void  update(ClazzQueryParam1 clazz);

    List<Clazz> list(ClazzQueryParam clazzQueryParam);
}