package com.oglcnkrty.controller;

import com.oglcnkrty.dto.DtoEmployee;

public interface IRestEmployeeController {
    public DtoEmployee getEmployeeById(Long id);

}
