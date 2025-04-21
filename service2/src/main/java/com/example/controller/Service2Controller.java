package com.example.controller;

import com.example.common.dto.ServiceInputDto;
import com.example.service.Service2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service2")
public class Service2Controller {
    private final Service2 service2;

    @PostMapping("/insert")
    public String insertEmp(@RequestBody ServiceInputDto dto) {
        service2.insertEmp(dto);
        return "emp inserted";
    }
}
