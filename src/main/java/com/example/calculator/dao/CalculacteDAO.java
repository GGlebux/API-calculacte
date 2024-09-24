package com.example.calculator.dao;

import com.example.calculator.models.Payment;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CalculacteDAO {

    private static final List<Calendar> holidays;

    static {
        holidays = new ArrayList<>();
        for (int day = 1; day <= 8; day++) {
            holidays.add(new GregorianCalendar(2024, Calendar.JANUARY, day)); // Новогодние праздники
        }
        holidays.add(new GregorianCalendar(2024, Calendar.FEBRUARY, 23));
        holidays.add(new GregorianCalendar(2024, Calendar.MARCH, 8));
        holidays.add(new GregorianCalendar(2024, Calendar.MAY, 1));
        holidays.add(new GregorianCalendar(2024, Calendar.MAY, 9));
        holidays.add(new GregorianCalendar(2024, Calendar.JUNE, 12));
        holidays.add(new GregorianCalendar(2024, Calendar.NOVEMBER, 4));
    }

    public String calculation(Payment payment, Model model) {

        Double salary = payment.getSalary();
        Integer vacation = payment.getVacation();
        String startVacation = payment.getDate();
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
                    if (holidays.contains(calendar)) {
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
