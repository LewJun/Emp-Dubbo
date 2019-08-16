package com.example.lewjun.service.impl;

import com.example.lewjun.bean.po.Emp;
import com.example.lewjun.service.EmpService;
import com.example.lewjun.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author LewJun
 */
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public int save(Emp emp) {
        return empMapper.save(emp);
    }

    @Override
    public int del(Serializable pk) {
        return empMapper.del(pk);
    }

    @Override
    public int update(Emp emp) {
        return empMapper.update(emp);
    }

    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    @Override
    public Emp findByPk(Serializable pk) {
        return empMapper.findByPk(pk);
    }
}
