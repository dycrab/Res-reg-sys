package com.levia.resregsys.controller;

import com.levia.resregsys.bean.Department;
import com.levia.resregsys.bean.Doctor;
import com.levia.resregsys.bean.Parts;
import com.levia.resregsys.bean.RegisterData;
import com.levia.resregsys.dao.DepartmentDao;
import com.levia.resregsys.dao.RegisterDataDao;
import com.levia.resregsys.dao.UserInsertDao;
import com.levia.resregsys.service.UserService;
import org.apache.http.HttpResponse;
import org.apache.ibatis.javassist.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;


    @Resource
    UserInsertDao userInsertDao;

    @Resource
    RegisterDataDao registerDataDao;

    @Resource
    private DepartmentDao departmentDao;

    private static final List<String> departmentType = Arrays.asList("儿科", "耳鼻咽喉头颈外科","眼科",
            "感染病科","骨科","整形美容科","肿瘤科","口腔科","全科","外科","中医科","急诊科","内科","妇产科","中西医结合科");


    /*
        该函数可以返回获得医生的信息
     */

    @ResponseBody
    @GetMapping("/doctorPage/{partType}/{currentPage}/{pageSize}")
    public List<Doctor> selectFindBySql(@PathVariable("currentPage") int currentPage,
                                        @PathVariable("pageSize") int pageSize,
                                        @PathVariable("partType") String partType){


        List<Doctor> list = userService.selectUserBySql(currentPage,pageSize,partType);
        if (list==null){
            throw new UserNotExistException("访问出错!QWQ");
        }else{
            return list;
        }
    }


    /*
        该函数接收预约人的挂红请求，并进行实名认证
     */
    @ResponseBody
    @GetMapping("/register/{name}/{idCard}/{birth}/{sexType}/{doctorId}/{doctorName}/{partType}/{registerDate}")
    public String registerTest(@PathVariable("name") String name,
                               @PathVariable("idCard") String idCard,
                               @PathVariable("birth") String birth,
                               @PathVariable("sexType") String sexType,
                               @PathVariable("doctorId") int doctorId,
                               @PathVariable("doctorName") String doctorName,
                               @PathVariable("partType") String partType,
                               @PathVariable("registerDate") String registerDate) throws ParseException {

        RegisterData registerData = new RegisterData();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Timestamp today = new Timestamp(simpleDateFormat.parse(registerDate).getTime());
        String today2 = today.toString().substring(0, today.toString().indexOf("."));

        registerData.setRegisterDate(today2);
        registerData.setDoctorId(doctorId);
        registerData.setIdCard(idCard);
        registerData.setDoctorName(doctorName);
        registerData.setPartType(partType);
        registerData.setPatientName(name);
        registerData.setSexType(sexType);
        userInsertDao.insertRegister(registerData);

        return "预约成功";

        /*
        这里注释掉了实名认证的代码块，节省在调试的时候使用该功能
         */

//        String resp = verifyId(name,idCard);
//        if (resp == null){
//            return "认证错误";
//        }else{
//            RegisterData registerData = new RegisterData();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date date =  new Date(simpleDateFormat.parse(birth).getTime()) ;
//
//
//            Date today = new Date(simpleDateFormat.parse(registerDate).getTime());
//
//            registerData.setRegisterDate(today);
//            registerData.setDoctorId(doctorId);
//            registerData.setIdCard(idCard);
//            registerData.setDoctorName(doctorName);
//            registerData.setPartType(partType);
//            registerData.setPatientName(name);
//            registerData.setSexType(sexType);
//            userInsertDao.insertRegister(registerData);
//
//            return "预约成功";
//        }
    }


    public String verifyId(String name, String idCard) {
        String host = "https://zidv2.market.alicloudapi.com";
        String path = "/idcheck/Post";
        String method = "POST";
        String appcode = "1a2fdf6dd9ab4efdbdcc60b3e4d18b35";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("cardNo", idCard);
        bodys.put("realName", name);


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            return response.toString();
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @ResponseBody
    @GetMapping("/sideTitle")
    public List<Parts> sideTitle(){
        List<Department> departmentList = departmentDao.getDepartmentList();
        List<Parts> parts = new ArrayList<>();
        for(String type:departmentType){
            Parts tmp = new Parts();
            tmp.setPartsName(type);
            tmp.part = new ArrayList<>();
            for (Department department:departmentList){
                if (department.getType().equals(type)){
                    tmp.part.add(department);
                }
            }
            parts.add(tmp);
        }
        return parts;


    }

    @ResponseBody
    @GetMapping("/haveRegisterDate/{doctorId}/{date}")
    public String haveRegisterDate(@PathVariable("doctorId") int doctorId,
                                   @PathVariable("date") String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

         Date date1 = new Date(formatter.parse(date).getTime());
         int resp = registerDataDao.haveOrNotData(doctorId, date1);
         if (resp > 0){
             return "0";
         }else{
             return "1";
         }
    }

    @ResponseBody
    @GetMapping("/countRegisterDate/{doctorId}/{date}")
    public int countRegisterDate(@PathVariable("doctorId") int doctorId,
                                   @PathVariable("date") String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Timestamp date1 = new Timestamp(formatter.parse(date).getTime());
        String date2 = date1.toString().substring(0, date1.toString().indexOf("."));
        return registerDataDao.countData(doctorId, date2);
    }
}
