package com.levia.resregsys.service;

import com.levia.resregsys.bean.Doctor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;



public interface UserService {

    int selectCount();

    /**
     * 原生分页
     * @param currentPage
     * @param pageSize
     * @return
     */


    /**
     * 通过sql分页
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Doctor> selectUserBySql(int currentPage,int pageSize, String partType);
}
