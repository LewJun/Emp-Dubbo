package com.example.lewjun.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmpVO extends BaseVO implements Serializable {

    private Integer empno;

    private String ename;
}
