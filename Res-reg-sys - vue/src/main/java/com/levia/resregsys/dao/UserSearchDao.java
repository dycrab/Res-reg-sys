package com.levia.resregsys.dao;

import com.levia.resregsys.bean.Doctor;
import com.levia.resregsys.bean.User;
import com.levia.resregsys.pojo.PartDoctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.print.Doc;
import java.util.List;


@Mapper
public interface UserSearchDao {
    Doctor selectDoctor(String username);
    User selectPatient(String username);
    int selectCount();
    List<Doctor> getDoctorList();
    List<User> getUserList();
    List<Doctor> selectDoctorName(String name);
    List<Doctor> selectDoctorType(String type);
    Doctor selectDoctorUid(String uid);
    List<PartDoctor> getDepartmentDoctor(String id);
    String getAdminPass();
}
