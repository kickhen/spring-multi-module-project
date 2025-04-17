package com.example.service;

import com.example.common.dto.EmpDto;
import com.example.common.dto.ServiceInputDto;
import com.example.common.mappers.postgres.EmpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class Service1 {
    private final EmpMapper empMapper;
    private final RestTemplate restTemplate;

    @Transactional(transactionManager = "jtaTransactionManager")
    public void insertEmp(ServiceInputDto dto) {
        empMapper.insertEmp(dto.getEmpDto());
        dto.getEmpDto().setEmpno(0);
        ResponseEntity<String> response = restTemplate.postForEntity("http://192.168.12.45:8082/insert", dto, String.class);
        System.out.println(response.getBody());
        if (dto.isTriggerError()) {
            throw new RuntimeException("trigger error");
        }
    }
}
