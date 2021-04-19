package com.file.reader.configuration;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import com.file.reader.entity.Employee;

@Component
public class BalanceFieldSetMapper implements FieldSetMapper<Employee> {

    @Override
    public Employee mapFieldSet(FieldSet fieldSet) {
        final Employee employee = new Employee();
        employee.setName(fieldSet.readString("name"));
        employee.setBranch(fieldSet.readString("branch"));
        employee.setDate(fieldSet.readString("date"));
        employee.setCountry(fieldSet.readString("country"));
        employee.setSalaryamt(fieldSet.readBigDecimal("salaryamt"));
        employee.setBonusid(fieldSet.readString("bonusid"));
        employee.setBonusamt(fieldSet.readBigDecimal("bonusamt"));
        return employee;
    }
}
