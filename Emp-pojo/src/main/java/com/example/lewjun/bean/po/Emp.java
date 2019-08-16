package com.example.lewjun.bean.po;

import java.io.Serializable;

/**
 * @author LewJun
 */
public class Emp extends BasePO implements Serializable {

    private Integer empno;

    private String ename;

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
}