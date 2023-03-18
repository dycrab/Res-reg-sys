package com.levia.resregsys.mapper;

import com.levia.resregsys.bean.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    //原生分页
    int selectCount();
    //通过sql语句进行分页
    List<Doctor> selectBySql(Map<String,Object> map);
}
