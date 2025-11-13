package com.oglcnkrty.service;

import com.oglcnkrty.dto.DtoEmployee;
import org.springframework.stereotype.Service;

@Service
public interface IEmployeeService{
    public DtoEmployee getEmployeeById(Long id);


}
