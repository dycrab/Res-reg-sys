package com.levia.resregsys.bean;
import lombok.*;

import java.sql.Date;

//set,get方法，构造方法有参无参，toString方法
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorAllData {
    public int doctorId;
    public String doctorName;
    public String PTR;
    public String password;
    public String partType;
    public String using;
    public String sex;
    public Date birth;
    public int partTypeId;
    public String good;

}
