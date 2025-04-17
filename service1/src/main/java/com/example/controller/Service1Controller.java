package com.example.controller;

import com.example.common.dto.ServiceInputDto;
import com.example.service.Service1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Service1Controller {
    private final Service1 service1;

    @PostMapping("/insert")
    public String insertEmp(@RequestBody ServiceInputDto dto) {
        service1.insertEmp(dto);
        return "emp inserted";
    }
}
