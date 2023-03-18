package com.levia.resregsys.bean;

import lombok.*;
//set,get方法，构造方法有参无参，toString方法
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department {
    public int id;
    public String partType;
    public String type;
}
