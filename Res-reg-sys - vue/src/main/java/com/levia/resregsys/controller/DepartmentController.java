//package com.levia.resregsys.controller;
//
//
//import com.levia.resregsys.bean.Department;
//import com.levia.resregsys.bean.Parts;
//import com.levia.resregsys.dao.DepartmentDao;
//import com.levia.resregsys.dao.UserSearchDao;
//import com.levia.resregsys.pojo.PartDoctor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.annotation.Resource;
//import java.util.*;
//
//@Controller
//public class DepartmentController {
//
//    @Resource
//    private DepartmentDao departmentDao;
//
//    @Resource
//    private UserSearchDao userSearchDao;
//
//
//    private static final List<String> departmentType = Arrays.asList("儿科", "耳鼻咽喉头颈外科","眼科",
//            "感染病科","骨科","整形美容科","肿瘤科","口腔科","全科","外科","中医科","急诊科","内科","妇产科","中西医结合科");
//
//    @GetMapping(value = "/department")
//    public String test(Model model){
//        return "redirect:/department/part1.html";
//    }
//
//    @GetMapping(value = "/department/part1.html")
//    public String redirectDepartment(Model model){
//        List<Department> departmentList = departmentDao.getDepartmentList();
//        List<Parts> parts = new ArrayList<>();
//        for(String type:departmentType){
//            Parts tmp = new Parts();
//            tmp.setPartsName(type);
//            tmp.part = new ArrayList<>();
//            for (Department department:departmentList){
//                if (department.getType().equals(type)){
//                    tmp.part.add(department);
//                }
//            }
//            parts.add(tmp);
//        }
//        Parts tmp = new Parts();
//        tmp.setPartsName("热门科室");
//        tmp.part = new ArrayList<>();
//        for (Department department:departmentList){
//            if (department.getType().equals(tmp.getPartsName())){
//                tmp.part.add(department);
//            }
//        }
//        model.addAttribute("hot",tmp);
//        model.addAttribute("parts",parts);
//        return "department/part1";
//    }
//
//
//
//    @GetMapping("/part/")
//    public String getDepartmentID(@RequestParam(name = "id") String departmentID, Model model){
//        List<PartDoctor> partDoctors = userSearchDao.getDepartmentDoctor(departmentID);
//        model.addAttribute("partDoctors",partDoctors);
//
//        return "department/department";
//    }
//
//
//
//
//}
