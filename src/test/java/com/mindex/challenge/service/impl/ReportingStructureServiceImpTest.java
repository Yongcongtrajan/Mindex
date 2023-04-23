package com.mindex.challenge.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImpTest {

    private String employeeUrl;
    private String employeeIdUrl;
    private String reportingStructureUrl;


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
        reportingStructureUrl = "http://localhost:" + port + "/employee/{id}/reporting-structure";
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testReportingStructureService() {

     /*   Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");


        Employee testEmployee1 = new Employee();
        testEmployee1.setFirstName("Paul");
        testEmployee1.setLastName("McCartney");
        testEmployee1.setDepartment("Engineering");
        testEmployee1.setPosition("New-Grad");


        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();
        Employee createdEmployee1 = restTemplate.postForEntity(employeeUrl, testEmployee1, Employee.class).getBody();

        List<Employee> directReportsList = new ArrayList<>();
        directReportsList.add(createdEmployee1);

        createdEmployee.setDirectReports(directReportsList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.exchange(employeeIdUrl, HttpMethod.PUT, new HttpEntity<Employee>(createdEmployee, headers), Employee.class, createdEmployee.getEmployeeId());



        ReportingStructure reportingStructure = restTemplate.getForEntity(reportingStructureUrl,ReportingStructure.class,createdEmployee.getEmployeeId()).getBody();
        assertEquals(Objects.requireNonNull(reportingStructure).getNumberOfReports(),1);
        ReportingStructure reportingStructure1 = restTemplate.getForEntity(reportingStructureUrl,ReportingStructure.class,createdEmployee1.getEmployeeId()).getBody();
        assertEquals(Objects.requireNonNull(reportingStructure1).getNumberOfReports(),0);*/



        ReportingStructure reportingStructure0 = restTemplate.getForEntity(reportingStructureUrl,ReportingStructure.class,"16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();
        assertEquals(4,Objects.requireNonNull(reportingStructure0).getNumberOfReports());
        ReportingStructure reportingStructure1 = restTemplate.getForEntity(reportingStructureUrl,ReportingStructure.class,"b7839309-3348-463b-a7e3-5de1c168beb3").getBody();
        assertEquals(0,Objects.requireNonNull(reportingStructure1).getNumberOfReports());
        ReportingStructure reportingStructure2 = restTemplate.getForEntity(reportingStructureUrl,ReportingStructure.class,"03aa1462-ffa9-4978-901b-7c001562cf6f").getBody();
        assertEquals(2,Objects.requireNonNull(reportingStructure2).getNumberOfReports());
        ReportingStructure reportingStructure3 = restTemplate.getForEntity(reportingStructureUrl,ReportingStructure.class,"62c1084e-6e34-4630-93fd-9153afb65309").getBody();
        assertEquals(0,Objects.requireNonNull(reportingStructure3).getNumberOfReports());
        ReportingStructure reportingStructure4 = restTemplate.getForEntity(reportingStructureUrl,ReportingStructure.class,"c0c2293d-16bd-4603-8e08-638a9d18b22c").getBody();
        assertEquals(0,Objects.requireNonNull(reportingStructure4).getNumberOfReports());






    }

}