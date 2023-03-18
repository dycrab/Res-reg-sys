package com.levia.resregsys.dao;

import com.levia.resregsys.bean.Doctor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserModifyDao {

    void modifyDoctor(Doctor doctor);

}
