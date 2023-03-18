package com.levia.resregsys.service;

import com.levia.resregsys.bean.Doctor;
import com.levia.resregsys.mapper.UserMapper;
import com.levia.resregsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int selectCount() {
        int count = userMapper.selectCount();
        return count;
    }


    /**
     *通过sql语句进行分页
     */
    @Override
    public List<Doctor> selectUserBySql(int currentPage, int pageSize, String partType) {
        Map<String,Object> map=new HashMap<>();
        int startCurrentPage=(currentPage-1)*pageSize;        //从第几个数据开始
        int count=selectCount();
        int totalPage=count/pageSize;                   //总页数
        if (currentPage>totalPage || currentPage<=0){
            return null;
        }else{
            map.put("currentPage",startCurrentPage);
            map.put("pageSize",pageSize);
            map.put("partType",partType);
            List<Doctor> list = userMapper.selectBySql(map);
            return list;
        }

    }
}

