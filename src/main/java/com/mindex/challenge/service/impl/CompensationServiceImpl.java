package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
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
        compensation.setEmployee(employeeRepository.findByEmployeeId(employeeId));
        return compensationRepository.insert(compensation);
    }

    @Override
    public Compensation read(String employeeId) {
        return compensationRepository.findByEmployeeEmployeeId(employeeId);
    }
}
