package com.levia.resregsys.dao;

import com.levia.resregsys.bean.RegisterData;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface RegisterDataDao {
    List<RegisterData> getRegisterList();
    List<RegisterData> getRegisterByDate(Date registerDate);
    List<RegisterData> getRegisterByIdCard(String idCard);
    List<RegisterData> getRegisterByDoctor(String doctorName);
    List<RegisterData> getRegisterByDoctorId(int doctorId, Date date);
    List<RegisterData> getRegisterPage(int currentPage, int pageSize);
    int getDoctorTodayCount(Date date, int doctorId);
    int getDoctorTodayCountNum(Date date, int doctorId);
    void modifyDoctorTodayCountNum(int doctorId, int nowIndex);
    int haveOrNotData(int doctorId, Date date);
    int countData(int doctorId, String date);
}
