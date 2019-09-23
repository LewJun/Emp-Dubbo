package com.example.lewjun.mapper;


import com.example.lewjun.dto.EmpDTO;

import java.io.Serializable;
import java.util.List;

public interface EmpMapper {
    int save(EmpDTO emp);

    int del(Serializable pk);

    int update(EmpDTO emp);

    List<EmpDTO> findAll();

    EmpDTO findByPk(Serializable pk);
}
