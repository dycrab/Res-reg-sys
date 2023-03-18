package com.levia.resregsys.dao;

import com.levia.resregsys.bean.Doctor;
import com.levia.resregsys.bean.DoctorAllData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DoctorDataDao {
    List<DoctorAllData> getDoctorAllData();
    DoctorAllData getDoctorAllDataById(int doctorId);
    List<DoctorAllData> getDoctorDataByName(String doctorName);
    List<DoctorAllData> getDoctorDataByPartType(String partType);
    int getPartTypeId(String partType);
    void addDoctor(DoctorAllData doctorAllData);
    void modifyDoctor(DoctorAllData doctorAllData);
    void deleteDoctor(int doctorId);
    int accountTest(int doctorId);
    int passwordTest(int doctorId, String password);
}
