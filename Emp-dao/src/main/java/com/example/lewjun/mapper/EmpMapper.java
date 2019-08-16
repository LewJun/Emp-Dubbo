package com.example.lewjun.mapper;


import com.example.lewjun.bean.po.Emp;

import java.io.Serializable;
import java.util.List;

public interface EmpMapper {
    int save(Emp emp);

    int del(Serializable pk);

    int update(Emp emp);

    List<Emp> findAll();

    Emp findByPk(Serializable pk);
}
