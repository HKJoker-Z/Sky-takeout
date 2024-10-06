package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * pagination
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Enable and Disable employee account
     * @param status
     * @param id
     * @return
     */
    void startOnStop(Integer status, Long id);


    /**
     * Select employee by id
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * update employee
     * @param employeeDTO
     * @return
     */
    void update(EmployeeDTO employeeDTO);
}
