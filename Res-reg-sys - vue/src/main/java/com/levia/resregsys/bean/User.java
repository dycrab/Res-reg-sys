package com.levia.resregsys.bean;

import lombok.*;
//set,get方法，构造方法有参无参，toString方法
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String uid;
    private String username;
    private String password;
    private String email;
}