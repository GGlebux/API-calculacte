package com.example.calculator.controllers;


import com.example.calculator.models.CalendarHolidays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


@Controller()
public class MainController {


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(Model model) {
        model.addAttribute("error", "Invalid salary or vacation");
        return "error";
    }


    @GetMapping("/calculacte")
    public String calculacte(@RequestParam(name = "salary", required = false) Double salary,
                             @RequestParam(name = "vacation", required = false) Integer vacation,
                             @RequestParam(name = "date", required = false) String startVacation, Model model) {

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
            return "calculacte";

        }
        model.addAttribute("error", "Invalid salary or vacation");
        return "error";
    }
}
