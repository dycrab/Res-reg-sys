package com.levia.resregsys.bean;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

//set,get方法，构造方法有参无参，toString方法
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterData {
    public int registerId;
    public String idCard;
    public String patientName;
    public String sexType;
    public int doctorId;
    public String doctorName;
    public String partType;
    public String registerDate;


}
