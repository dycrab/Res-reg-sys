package com.levia.resregsys.dao;


import com.levia.resregsys.bean.Doctor;
import com.levia.resregsys.bean.RegisterData;
import com.levia.resregsys.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInsertDao {

    void insertPatient(User user);
    void insertDoctor(Doctor doctor);
    void insertRegister(RegisterData registerData);
}
