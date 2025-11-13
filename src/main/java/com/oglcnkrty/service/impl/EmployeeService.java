package com.oglcnkrty.service.impl;

import com.oglcnkrty.dto.DtoDepartment;
import com.oglcnkrty.dto.DtoEmployee;
import com.oglcnkrty.model.Employee;
import com.oglcnkrty.repository.EmployeeRepository;
import com.oglcnkrty.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DtoEmployee getEmployeeById(Long id) {
        System.out.println("Auth in controller: " + SecurityContextHolder.getContext().getAuthentication());

        DtoEmployee dtoEmployee = new DtoEmployee();
        DtoDepartment dtoDepartment = new DtoDepartment();
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            BeanUtils.copyProperties(employee.get(), dtoEmployee);
            BeanUtils.copyProperties(employee.get().getDepartment(), dtoDepartment);
            dtoEmployee.setDepartment(dtoDepartment);

            return dtoEmployee;
        }
        return null;
    }
}
