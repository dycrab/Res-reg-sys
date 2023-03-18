package com.levia.resregsys.controller;


import com.levia.resregsys.bean.Doctor;
import com.levia.resregsys.bean.DoctorAllData;
import com.levia.resregsys.bean.RegisterData;
import com.levia.resregsys.bean.User;
import com.levia.resregsys.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;

@Controller
public class AdminController {
    @Resource
    private RegisterDataDao registerDataDao;

    @Resource
    private DoctorDataDao doctorDataDao;

    @Resource
    private UserSearchDao userSearchDao;

    @ResponseBody
    @GetMapping(value = "/login_admin/{password}")
    public String adminPage(@PathVariable("password") String password){

        String pass = userSearchDao.getAdminPass();

        if(password.equals(pass)) {

            return "密码正确";
        }else{

            return "密码错误";
        }
    }

    @ResponseBody
    @GetMapping("/registerData")
    public List<RegisterData>  registerData(){
        return registerDataDao.getRegisterList();
    }
    @ResponseBody
    @GetMapping("/registerData/{currentPage}/{pageSize}")
    public List<RegisterData>  registerDataPage(@PathVariable("currentPage") int currentPage,
                                                @PathVariable("pageSize") int pageSize){
        return registerDataDao.getRegisterPage(currentPage-1,pageSize);
    }

    @ResponseBody
    @GetMapping("/registerDataByDate/{registerDate}")
    public List<RegisterData> registerDataByDate(@PathVariable("registerDate") Date registerDate){
        return registerDataDao.getRegisterByDate(registerDate);
    }

    @ResponseBody
    @GetMapping("/registerDataByIdCard/{idCard}")
    public List<RegisterData> registerDataByIdCard(@PathVariable("idCard") String idCard){
        return registerDataDao.getRegisterByIdCard(idCard);
    }

    @ResponseBody
    @GetMapping("/registerDataByDoctor/{doctorName}")
    public List<RegisterData> registerDataByDoctor(@PathVariable("doctorName") String doctorName){
        return registerDataDao.getRegisterByDoctor(doctorName);
    }

    @ResponseBody
    @GetMapping("/getDoctorAllData")
    public List<DoctorAllData> getDoctorAllData(){
        return doctorDataDao.getDoctorAllData();
    }

    @ResponseBody
    @GetMapping("/getDoctorData/{doctorName}")
    public List<DoctorAllData> getDoctorDataByName(@PathVariable("doctorName") String doctorName){
        return doctorDataDao.getDoctorDataByName(doctorName);
    }
    @ResponseBody
    @GetMapping("/getDoctorDataByPart/{partType}")
    public List<DoctorAllData> getDoctorDataByNameByPartType(@PathVariable("partType") String partType){
        return doctorDataDao.getDoctorDataByPartType(partType);
    }

    @ResponseBody
    @GetMapping("/getPartTypeId/{partType}")
    public  int getPartTypeId(@PathVariable("partType") String partType){
        return doctorDataDao.getPartTypeId(partType);
    }

    @ResponseBody
    @PostMapping("/addDoctor")
    public  String addDoctor(@RequestBody DoctorAllData doctorAllData){
        doctorDataDao.addDoctor(doctorAllData);

        return "添加成功！";
    }

    @ResponseBody
    @PostMapping("/modifyDoctor")
    public  String modifyDoctor(@RequestBody DoctorAllData doctorAllData){
        DoctorAllData doctorAllData1 = doctorDataDao.getDoctorAllDataById(doctorAllData.getDoctorId());

        if (doctorAllData.getDoctorName().length() == 0){
            doctorAllData.setDoctorName(doctorAllData1.getDoctorName());
        }
        if (doctorAllData.getPTR().length() == 0){
            doctorAllData.setPTR(doctorAllData1.getPTR());
        }
        if (doctorAllData.getPassword().length() == 0){
            doctorAllData.setPassword(doctorAllData1.getPassword());
        }
        if (doctorAllData.getPartType().length() == 0){
            doctorAllData.setPartType(doctorAllData1.getPartType());
        }
        if (doctorAllData.getUsing().length() == 0){
            doctorAllData.setUsing(doctorAllData1.getUsing());
        }
        if (doctorAllData.getSex().length() == 0){
            doctorAllData.setSex(doctorAllData1.getSex());
        }
        if (doctorAllData.getBirth() == null){
            doctorAllData.setBirth(doctorAllData1.getBirth());
        }
        if (doctorAllData.getPartTypeId() == 0){
            doctorAllData.setPartTypeId(doctorAllData1.getPartTypeId());
        }
        if (doctorAllData.getGood().length() == 0){
            doctorAllData.setGood(doctorAllData1.getGood());
        }

        doctorDataDao.modifyDoctor(doctorAllData);

        return "修改成功！";
    }


    @ResponseBody
    @GetMapping("/deleteDoctor/{doctorId}")
    public String deleteDoctor(@PathVariable("doctorId") int doctorId){
        doctorDataDao.deleteDoctor(doctorId);
        return "删除成功";
    }

//    @PostMapping("/addDoctor")
//    public String addDoctor(DoctorAllData doctorAllData){
//
//        System.
//        return "1";
//    }


//
//    @GetMapping(value = "/admin_main.html")
//    public String adminPageR(HttpSession session){
//        if (session.getAttribute("LoginAdmin")!=null){
//            return "admin_main";
//        }else{
//            return "login_admin";
//        }
//    }
//
//    @GetMapping(value = "/doctor_administration")
//    public String doctorAdmin(HttpSession session, Model model){
//        if (session.getAttribute("LoginAdmin")!=null){
//            return "admin/doctor_administration";
//        }else{
//            return "login_admin";
//        }
//    }
//    @GetMapping(value = "/doctor_table")
//    public String doctorTable(HttpSession session, Model model){
//        if (session.getAttribute("LoginAdmin")!=null){
//            List<Doctor> users = userSearchDao.getDoctorList();
//            model.addAttribute("users",users);
//            return "admin/doctor_table";
//        }else{
//            return "login_admin";
//        }
//    }
//
//
//    @GetMapping(value = "/patient_administration")
//    public String patientAdmin(HttpSession session, Model model){
//        if (session.getAttribute("LoginAdmin")!=null){
//            return "admin/patient_administration";
//        }else{
//            return "login_admin";
//        }
//    }
//
//    @GetMapping(value = "/patient_table")
//    public String patientTable(HttpSession session, Model model){
//        if (session.getAttribute("LoginAdmin")!=null){
//            List<User> users = userSearchDao.getUserList();
//            model.addAttribute("users",users);
//            return "admin/patient_table";
//        }else{
//            return "login_admin";
//        }
//    }
//
//    @PostMapping(value = "/add_doctor")
//    public String addDoctorAccount(Doctor doctor, Model model,HttpSession session){
//        List<Doctor> users = userSearchDao.getDoctorList();
//        if(doctor.getPassword().length()!=0&&doctor.getPartType().length()!=0&&doctor.getDoctorName().length()!=0){
//            doctorCount = users.size();
//            doctorCount = doctorCount + 1;
//            doctor.setDoctorId(Integer.toString(doctorCount));
//            doctor.setUsing("1");
//            userInsertDao.insertDoctor(doctor);
//            return "redirect:admin/doctor_administration.html";
//        }else{
//            model.addAttribute("users",users);
//            model.addAttribute("msg","操作错误！");
//            return "admin/doctor_administration";
//        }
//
//    }
//
//    @GetMapping(value = "/admin/doctor_administration.html")
//    public String redirectDoctorAdministration(Model model){
//        model.addAttribute("msg","操作成功！");
//        List<Doctor> users = userSearchDao.getDoctorList();
//        model.addAttribute("users",users);
//        return "admin/doctor_administration";
//    }
//
//
//    @PostMapping(value = "/delete_doctor")
//    public String deleteDoctorAccount(Doctor doctor, Model model,HttpSession session){
//        List<Doctor> users = userSearchDao.getDoctorList();
//        doctor.setUsing("0");
//        if(doctor.getDoctorId().length()!=0){
//            if(userSearchDao.selectDoctorUid(doctor.getDoctorId())!=null){
//                userDeleteDao.deleteDoctorByUid(doctor);
//                return "redirect:admin/doctor_administration.html";
//            }else{
//                model.addAttribute("users",users);
//                model.addAttribute("msg","操作错误！");
//                return "admin/doctor_administration";
//            }
//        }else if(doctor.getPartType().length()!=0){
//            if(userSearchDao.selectDoctorType(doctor.getPartType())!=null){
//                userDeleteDao.deleteDoctorByType(doctor);
//                return "redirect:admin/doctor_administration.html";
//            }else{
//                model.addAttribute("users",users);
//                model.addAttribute("msg","操作错误！");
//                return "admin/doctor_administration";
//            }
//        }else if(doctor.getDoctorName().length()!=0){
//            if(userSearchDao.selectDoctorUid(doctor.getDoctorName())!=null){
//                userDeleteDao.deleteDoctorByUid(doctor);
//                return "redirect:admin/doctor_administration.html";
//            }else{
//                model.addAttribute("users",users);
//                model.addAttribute("msg","操作错误！");
//                return "admin/doctor_administration";
//            }
//        }
//        else{
//            model.addAttribute("users",users);
//            model.addAttribute("msg","操作错误！");
//            return "admin/doctor_administration";
//        }
//
//    }
//
//    @PostMapping(value = "/modify_doctor")
//    public String modifyDoctorInfo(Doctor doctor, Model model){
//        List<Doctor> users = userSearchDao.getDoctorList();
//        if (doctor.getUsing().length()!=0 && doctor.getPartType().length()!=0 && doctor.getDoctorId().length()!=0
//        && doctor.getDoctorName().length()!=0 && doctor.getPassword().length()!=0){
//            userModifyDao.modifyDoctor(doctor);
//            return "redirect:admin/doctor_administration.html";
//        }else{
//            model.addAttribute("users",users);
//            model.addAttribute("msg","输入不能有空！");
//            return "admin/doctor_administration";
//        }
//    }
//
}
