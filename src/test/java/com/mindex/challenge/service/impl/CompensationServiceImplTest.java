package com.mindex.challenge.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
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
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.springframework.web.client.HttpClientErrorException;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationUrl;

    Date currentDate = new Date();



    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @Autowired
    private CompensationService compensationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation/{employeeId}";
    }

   /* @Test
    public void testCompensation() {
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        Employee testEmployee = employeeRepository.findByEmployeeId(employeeId);

        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);

        testCompensation.setSalary(150000);
        testCompensation.setEffectiveDate(currentDate);

        ResponseEntity<Void> response = restTemplate.postForEntity(compensationUrl, testCompensation, Void.class, employeeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<Compensation> responseEntity = restTemplate.getForEntity(compensationUrl, Compensation.class, employeeId);

        Compensation fetchedCompensation = responseEntity.getBody();
        assertNotNull(fetchedCompensation);
        assertEquals(testCompensation.getEmployee().getEmployeeId(), fetchedCompensation.getEmployee().getEmployeeId());
        assertEquals(testCompensation.getSalary(), fetchedCompensation.getSalary(),0.00001);
        assertEquals(testCompensation.getEffectiveDate(), fetchedCompensation.getEffectiveDate());

        Compensation repositoryCheck = compensationRepository.findByEmployeeEmployeeId(employeeId);
        assertNotNull(repositoryCheck);
        assertEquals(testCompensation.getEmployee().getEmployeeId(), repositoryCheck.getEmployee().getEmployeeId());
        assertEquals(testCompensation.getSalary(), repositoryCheck.getSalary(),0.00001);
        assertEquals(testCompensation.getEffectiveDate(), repositoryCheck.getEffectiveDate());





    }*/

    @Test
    public void testCompensationCreateInvalidEmployeeId() {
        String invalidEmployeeId = "156161651651651";

        Compensation testCompensation = new Compensation();
        testCompensation.setSalary(150000);
        testCompensation.setEffectiveDate(currentDate);

        assertThrows(RuntimeException.class, () -> {
            compensationService.create(invalidEmployeeId, testCompensation);
        });


    }
    @Test
    public void testCompensationReadNoCompensationForEmployeeId() {
        String employeeIdWithoutCompensation = "222222222222222";

        Exception exception = assertThrows(RuntimeException.class, () -> compensationService.read(employeeIdWithoutCompensation));

        String expectedMessage = "Compensation not found for employeeId: " + employeeIdWithoutCompensation;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }





}