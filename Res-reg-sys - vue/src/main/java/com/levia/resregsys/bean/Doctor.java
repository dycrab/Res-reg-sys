package com.levia.resregsys.bean;

import lombok.*;

import java.sql.Date;

//set,get方法，构造方法有参无参，toString方法
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Doctor {
    private int doctorId;
    private String doctorName;
    private String PTR;
    private String partType;
    private String sex;
    private String good;
}