package com.levia.resregsys.dao;


import com.levia.resregsys.bean.Doctor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDeleteDao {
    void deleteDoctorByUid(Doctor doctor);
    void deleteDoctorByType(Doctor doctor);
}
