package com.levia.resregsys.bean;


import java.util.List;
import lombok.*;
//set,get方法，构造方法有参无参，toString方法
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Parts {
    public String partsName;
    public List<Department> part;
}
