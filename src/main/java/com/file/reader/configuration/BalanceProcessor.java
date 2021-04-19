package com.file.reader.configuration;


import org.springframework.batch.item.ItemProcessor;

import com.file.reader.entity.Employee;

public class BalanceProcessor implements ItemProcessor<Employee, Employee>{

    @Override
    public Employee process(final Employee employee) {
        final Employee processedEmployee = new Employee();
        processedEmployee.setName(employee.getName());
        processedEmployee.setBranch(employee.getBranch());
        processedEmployee.setDate(employee.getDate());
        processedEmployee.setCountry(employee.getCountry());
        processedEmployee.setSalaryamt(employee.getSalaryamt());
        processedEmployee.setBonusid(employee.getBonusid());
        processedEmployee.setBonusamt(employee.getBonusamt());
        return processedEmployee;
    }
}
