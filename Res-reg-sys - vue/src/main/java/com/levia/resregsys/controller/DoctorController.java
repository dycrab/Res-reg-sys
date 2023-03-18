package com.levia.resregsys.controller;


import com.levia.resregsys.bean.RegisterData;
import com.levia.resregsys.dao.DoctorDataDao;
import com.levia.resregsys.dao.RegisterDataDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class DoctorController {


    @Resource
    private RegisterDataDao registerDataDao;

    @Resource
    private DoctorDataDao doctorDataDao;

    Date today;


    /*
        该函数的功能为获得该医生在今天的挂号数据
     */

    @ResponseBody
    @GetMapping("/getRegisterData/{doctorId}")
    public List<RegisterData> getRegisterData(@PathVariable("doctorId") int doctorId) throws ParseException {
        java.util.Date date = new java.util.Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println();

        today = new Date(formatter.parse(formatter.format(date).toString()).getTime());
        return registerDataDao.getRegisterByDoctorId(doctorId, today);
    }

    /*
        该函数的功能为问诊页面加载时获得该医生当天问诊数量
        为了防止页面刷新导致的数据丢失
     */


    @ResponseBody
    @GetMapping("/getDoctorTodayCount/{doctorId}")
    public int getDoctorTodayCount(@PathVariable("doctorId") int doctorId) throws ParseException {
        java.util.Date date = new java.util.Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        today = new Date(formatter.parse(formatter.format(date).toString()).getTime());
        if (registerDataDao.getDoctorTodayCount(today,doctorId) == 1){
            return registerDataDao.getDoctorTodayCountNum(today, doctorId);
        }else{
            registerDataDao.modifyDoctorTodayCountNum(doctorId,0);
            return 0;
        }
    }

    /*
        该函数的功能为医生问诊完一个病人后，修改当天的问诊数
     */

    @ResponseBody
    @GetMapping("/modifyDoctorTodayCount/{doctorId}/{nowIndex}")
    public void modifyDoctorTodayCount(@PathVariable("doctorId") int doctorId,
                                       @PathVariable("nowIndex") int nowIndex){
        registerDataDao.modifyDoctorTodayCountNum(doctorId, nowIndex);
    }


    @ResponseBody
    @GetMapping("/login_doctor/{doctorId}/{password}")
    public String loginDoctor(@PathVariable("doctorId") int doctorId,
                              @PathVariable("password") String password){
        // 账号不存在
        if (doctorDataDao.accountTest(doctorId) == 0){
            return "账户不存在";
        }else if (doctorDataDao.passwordTest(doctorId, password) == 0){
            // 密码错误
            return "密码错误";
        }




        return "密码正确";
    }

}
