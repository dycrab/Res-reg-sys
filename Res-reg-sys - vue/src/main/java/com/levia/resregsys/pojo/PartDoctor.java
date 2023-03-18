package com.levia.resregsys.pojo;


import lombok.*;
//set,get方法，构造方法有参无参，toString方法
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PartDoctor {
    public String doctorId;
    public String username;
    public String partType;
}
