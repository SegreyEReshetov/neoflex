package com.example.vacationpaycalculator.serviceImpl;

import com.example.vacationpaycalculator.Utils.HolidaysDates;
import com.example.vacationpaycalculator.service.VacationPayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.*;
import java.util.*;

@Service
@AllArgsConstructor
public class VacationPayServiceImpl implements VacationPayService {


   private static final List<Calendar> holidaysDates = HolidaysDates.getHolidaysDates();
   private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    public BigDecimal getCalculateSum(int avgSalary, int countOfVacationDays, String startDate, String finishDate) throws ParseException {
        Calendar start = Calendar.getInstance();
        Calendar finish = Calendar.getInstance();
        BigDecimal avgSalaryInDay = new BigDecimal(avgSalary * 12 / 351.6d).setScale(2, RoundingMode.HALF_UP);
        if (startDate.equals("") || finishDate.equals(""))
            return new BigDecimal(avgSalaryInDay.doubleValue() * countOfVacationDays).setScale(2, RoundingMode.HALF_UP);

        start.setTime(df.parse(startDate));
        finish.setTime(df.parse(finishDate));

        finish.add(Calendar.DATE, 1);

        Integer countOfVacationDay = finish.get(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR);

        for (Calendar i = start; i.before(finish); i.add(Calendar.DATE, 1))
            if (isHoliday(i))
                countOfVacationDay--;


        return new BigDecimal(avgSalaryInDay.doubleValue() * countOfVacationDay).setScale(2, RoundingMode.HALF_UP);
    }

    private Boolean isHoliday(Calendar date) {
        Boolean ok = false;
        for (int i = 0; i < holidaysDates.size() && !ok; i++)
            ok = holidaysDates.get(i).get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)
                    && holidaysDates.get(i).get(Calendar.MONTH) == date.get(Calendar.MONTH);
        return ok;
    }
}
