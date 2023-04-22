package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImp implements ReportingStructureService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public ReportingStructure generate(Employee employee) {
        int numberOfReports = countReports(employee);
        return new ReportingStructure(employee, numberOfReports);
    }

    public int countReports(Employee employee) {
        int totalReports = 0;
        if (employee.getDirectReports() == null || employee.getDirectReports().isEmpty()) {
            return totalReports;
        }

        for (Employee directReport : employee.getDirectReports()) {
            Employee report = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
            totalReports++;
            totalReports += countReports(report);
        }
        return totalReports;
    }
}
