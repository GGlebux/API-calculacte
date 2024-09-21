package com.example.calculator.servises;

import com.example.calculator.models.CalendarHolidays;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Component
public class CalculacteServis {

    public String calculation(Double salary, Integer vacation, String startVacation, Model model) {
        int noPayDays = 0;

        if (Objects.nonNull(startVacation)) {
            try {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

                // Преобразовали строку в дату
                Date date = formatter.parse(startVacation);
                // Получили календарь из даты
                calendar.setTime(date);

                for (int i = 0; i < vacation; i++) {
                    if (CalendarHolidays.contains(calendar)) {
                        noPayDays++;
                    }
                    calendar.add(Calendar.DATE, 1);
                }
            } catch (ParseException e) {
                model.addAttribute("error", e.toString());
                return "error";
            }
        }

        if (Objects.nonNull(salary) && vacation > 0) {

            Double result = salary / 29.3 * (vacation - noPayDays);
            model.addAttribute("salary", salary);
            model.addAttribute("vacation", vacation);
            model.addAttribute("result", (int) Math.round(result));
            return "success";

        }
        model.addAttribute("error", "Invalid salary or vacation");
        return "error";
    }

}
