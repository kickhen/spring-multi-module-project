package com.example.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceInputDto {
    private boolean triggerError;
    private EmpDto empDto;
}
