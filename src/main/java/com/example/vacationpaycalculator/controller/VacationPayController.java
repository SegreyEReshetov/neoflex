package com.example.vacationpaycalculator.controller;

import com.example.vacationpaycalculator.service.VacationPayService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

@Controller
@AllArgsConstructor
public class VacationPayController {
    private VacationPayService vacationPayService;

    @GetMapping("/calculate")
    public ResponseEntity<BigDecimal> getVacationPay (@RequestParam(value = "salary", defaultValue = "0") Integer avgSalary,
                                                      @RequestParam(value = "days", defaultValue = "0") Integer countOfVacationDays,
                                                      @RequestParam(value = "start_date", defaultValue = "") String startDate,
                                                      @RequestParam(value = "finish_date", defaultValue = "") String finishDate) throws ParseException {
        return new ResponseEntity<>(vacationPayService.getCalculateSum(avgSalary, countOfVacationDays, startDate, finishDate), HttpStatus.OK);
    }

}
