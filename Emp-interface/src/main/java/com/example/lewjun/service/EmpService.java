package com.example.lewjun.service;

import com.example.lewjun.dto.EmpDTO;

import java.io.Serializable;
import java.util.List;

public interface EmpService {
    int save(EmpDTO emp);

    int del(Serializable pk);

    int update(EmpDTO emp);

    List<EmpDTO> findAll();

    EmpDTO findByPk(Serializable pk);
}
