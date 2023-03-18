//package com.levia.resregsys.controller;
//
//
//import com.levia.resregsys.bean.Doctor;
//import com.levia.resregsys.bean.User;
//import com.levia.resregsys.dao.UserInsertDao;
//import com.levia.resregsys.dao.UserSearchDao;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.List;
//
//@Controller
//public class IndexController {
//
//    @Resource
//    private UserSearchDao userSearchDao;
//    @Resource
//    private UserInsertDao userInsertDao;
//
//    @GetMapping(value = "/login_patient")
//    public String patientLoginPage(){
//
//        return "login_patient";
//    }
//
//    @GetMapping(value = "/login_doctor")
//    public String doctorLoginPage(){
//
//        return "login_doctor";
//    }
//
//    @GetMapping(value = "/register_patient")
//    public String patientRegisterPage(){
//
//        return "register_patient";
//    }
//
//    @PostMapping(value = "/login_patient")
//    public String patientLoginPost(User user, Model model, HttpSession session){
//        /**
//         * 账号密码判断
//         *
//         */
//        String username = user.getUsername();
//        String password = user.getPassword();
//
//        User user_pattern =  userSearchDao.selectPatient(username);
//
//        if(user_pattern == null || !(user_pattern.getPassword()).equals(password)) {
//            model.addAttribute("msg", "账号密码错误");
//            return "login_patient";
//        }
//
//        session.setAttribute("LoginPatient",user_pattern);
//        return "redirect:/department";
//
//    }
//
//    @PostMapping(value = "/login_doctor")
//    public String doctorLoginPost(Doctor doctor, Model model, HttpSession session){
//        /**
//         *
//         * 账号密码判断
//         *
//         */
//        String username = doctor.getDoctorName();
//        String password = doctor.getPassword();
//
//        Doctor doctor1 =  userSearchDao.selectDoctor(username);
//        if(doctor1 == null || !(doctor1.getPassword()).equals(password)) {
//            model.addAttribute("msg", "账号密码错误");
//            return "login_doctor";
//        }else if(doctor1.getUsing().equals("0")){
//            model.addAttribute("msg", "账号失效");
//            return "login_doctor";
//        }
//        session.setAttribute("LoginDoctor",doctor1);
//        return "redirect:/doctor_main.html";
//    }
//
//
//    @GetMapping(value = "/doctor_main.html")
//    public String doctorPage(HttpSession session){
//        if (session.getAttribute("LoginDoctor")!=null){
//            return "doctor_main";
//        }else{
//            return "index";
//        }
//    }
//    @GetMapping(value = "/patient_main.html")
//    public String patientPage(HttpSession session){
//        if (session.getAttribute("LoginPatient")!=null){
//            return "patient_main";
//        }else{
//            return "index";
//        }
//    }
//
//    @PostMapping(value = "/register_patient")
//    public String patientRegister(User user,Model model){
//        String username = user.getUsername();
//        user.setUid(Integer.toString(userSearchDao.selectCount()+1));
//        /**
//         * username重复判断
//         *
//         */
//        Doctor user_pattern = userSearchDao.selectDoctor(username);
//        if(user_pattern==null){
//            userInsertDao.insertPatient(user);
//            model.addAttribute("msg","注册成功，请登录！");
//            return "login_patient";
//        }else{
//            model.addAttribute("msg","该用户名已经存在!");
//            return "register_patient";
//        }
//    }
//}
