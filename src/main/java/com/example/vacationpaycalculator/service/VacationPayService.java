package com.example.vacationpaycalculator.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

public interface VacationPayService {

     BigDecimal getCalculateSum(int avgSalary, int countOfVacationDays, String startDate, String finishDate) throws ParseException;
}
