package com.levia.resregsys.dao;


import com.levia.resregsys.bean.Department;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.ManagedBean;
import java.util.List;

@Mapper
public interface DepartmentDao {
    List<Department> getDepartmentList();
}
