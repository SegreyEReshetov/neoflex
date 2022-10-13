package com.example.vacationpaycalculator;

import com.example.vacationpaycalculator.service.VacationPayService;
import com.example.vacationpaycalculator.serviceImpl.VacationPayServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.ParseException;

@SpringBootTest
class VacationPayCalculatorApplicationTests {

    @TestConfiguration
    static class VacationPayServiceImplTestContextConfiguration {

        @Bean
        public VacationPayService vacationPayService() {
            return new VacationPayServiceImpl();
        }
    }

    @Autowired
    private VacationPayService vacationPayService;

    @Test
    void checkWorkApiWithDay() throws ParseException {
        Integer salary = 40000;
        Integer days = 5;
        Assertions.assertEquals(vacationPayService.getCalculateSum(salary, days, "", "").doubleValue(), 6825.95);
    }

    @Test
    void checkWorkApiWithZeroSalary() throws ParseException {
        Integer salary = 0;
        Integer days = 5;
        Assertions.assertEquals(vacationPayService.getCalculateSum(salary, days, "", "").doubleValue(), 0);
    }

    @Test
    void checkWorkApiWithDate() throws ParseException {
        Integer salary = 40000;
        String startDate = "03/10/2022";
        String finishDate = "07/10/2022";
        Assertions.assertEquals(vacationPayService.getCalculateSum(salary, 0, startDate, finishDate).doubleValue(), 6825.95);
    }

    @Test
    void checkWorkApiWithOneHoliday() throws ParseException {
        Integer salary = 40000;
        String startDate = "09/01/2022";
        String finishDate = "14/01/2022";
        Assertions.assertEquals(vacationPayService.getCalculateSum(salary, 0, startDate, finishDate).doubleValue(), 6825.95);
    }

    @Test
    void checkWorkApiWithAllHoliday() throws ParseException {
        Integer salary = 40000;
        String startDate = "01/01/2022";
        String finishDate = "05/01/2022";
        Assertions.assertEquals(vacationPayService.getCalculateSum(salary, 0, startDate, finishDate).doubleValue(), 0);
    }





}
