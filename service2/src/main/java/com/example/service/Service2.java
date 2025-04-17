package com.example.service;

import com.example.common.dto.EmpDto;
import com.example.common.dto.ServiceInputDto;
import com.example.common.mappers.postgres.EmpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class Service2 {
    private final EmpMapper empMapper;

    @Transactional(transactionManager = "jtaTransactionManager")
    public void insertEmp(ServiceInputDto dto) {
        empMapper.insertEmp(dto.getEmpDto());
    }
}
