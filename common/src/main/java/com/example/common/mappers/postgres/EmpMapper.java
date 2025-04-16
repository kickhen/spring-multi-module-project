package com.example.common.mappers.postgres;

import com.example.common.dto.EmpDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper {
    void insertEmp(EmpDto dto);
}
