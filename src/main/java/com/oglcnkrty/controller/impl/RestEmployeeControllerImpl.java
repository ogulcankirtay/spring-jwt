package com.oglcnkrty.controller.impl;

import com.oglcnkrty.controller.IRestEmployeeController;
import com.oglcnkrty.dto.DtoEmployee;
import com.oglcnkrty.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/employee")
@RestController
public class RestEmployeeControllerImpl implements IRestEmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/{id}")
    public DtoEmployee getEmployeeById(@PathVariable(value = "id") Long id) {

        return employeeService.getEmployeeById(id);

    }

}
