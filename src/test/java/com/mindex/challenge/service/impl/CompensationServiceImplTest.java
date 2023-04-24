package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationUrl;

    Date currentDate = new Date();

    @Autowired
    private CompensationService compensationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation/{employeeId}";
    }

    @Test
    public void testCompensation() {
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        Employee testEmployee = employeeRepository.findByEmployeeId(employeeId);

        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);

        testCompensation.setSalary(150000);
        testCompensation.setEffectiveDate(currentDate);
        //send json payload with compensation
        ResponseEntity<Void> response = restTemplate.postForEntity(compensationUrl, testCompensation, Void.class, employeeId);
        //response should be 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        //read compensation json payload
        ResponseEntity<Compensation> responseEntity = restTemplate.getForEntity(compensationUrl, Compensation.class, employeeId);
        //compare expected and actual compensation
        Compensation fetchedCompensation = responseEntity.getBody();
        assertNotNull(fetchedCompensation);
        assertEquals(testCompensation.getEmployee().getEmployeeId(), fetchedCompensation.getEmployee().getEmployeeId());
        assertEquals(testCompensation.getSalary(), fetchedCompensation.getSalary(),0.00001);
        assertEquals(testCompensation.getEffectiveDate(), fetchedCompensation.getEffectiveDate());
        //check current value in the database
        Compensation repositoryCheck = compensationRepository.findByEmployeeEmployeeId(employeeId);
        assertNotNull(repositoryCheck);
        assertEquals(testCompensation.getEmployee().getEmployeeId(), repositoryCheck.getEmployee().getEmployeeId());
        assertEquals(testCompensation.getSalary(), repositoryCheck.getSalary(),0.00001);
        assertEquals(testCompensation.getEffectiveDate(), repositoryCheck.getEffectiveDate());





    }

    @Test //test invalid employee id exception
    public void testCompensationCreateInvalidEmployeeId() {
        String invalidEmployeeId = "156161651651651";

        Compensation testCompensation = new Compensation();
        testCompensation.setSalary(150000);
        testCompensation.setEffectiveDate(currentDate);

        assertThrows(RuntimeException.class, () -> {
            compensationService.create(invalidEmployeeId, testCompensation);
        });


    }
    @Test //test if no compensation returned
    public void testCompensationReadNoCompensationForEmployeeId() {
        String employeeIdWithoutCompensation = "222222222222222";

        Exception exception = assertThrows(RuntimeException.class, () -> compensationService.read(employeeIdWithoutCompensation));

        String expectedMessage = "Compensation not found for employeeId: " + employeeIdWithoutCompensation;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }





}