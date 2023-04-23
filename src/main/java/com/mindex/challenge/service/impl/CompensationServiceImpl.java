package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation create(String employeeId, Compensation compensation) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }
        compensation.setEmployee(employee);
        return compensationRepository.save(compensation);
    }

    @Override
    public Compensation read(String employeeId) {
        Compensation compensation = compensationRepository.findByEmployeeEmployeeId(employeeId);
        if (compensation == null) {
            throw new RuntimeException("Compensation not found for employeeId: " + employeeId);
        }
        return compensation;
    }
}
