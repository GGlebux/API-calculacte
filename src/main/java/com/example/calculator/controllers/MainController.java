package com.example.calculator.controllers;


import com.example.calculator.dao.CalculacteServis;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller()
public class MainController {
    private CalculacteServis calculacteServis;

    @GetMapping("/calculacte")
    public String calculacte(@RequestParam(name = "salary", required = false) Double salary,
                             @RequestParam(name = "vacation", required = false) Integer vacation,
                             @RequestParam(name = "date", required = false) String startVacation, Model model) {

        model.addAttribute("salary", salary);
        model.addAttribute("vacation", vacation);
        model.addAttribute("startVacation", startVacation);

        String answer = calculacteServis.calculation(salary, vacation, startVacation, model);

        if (answer == "success") {
            return "calculacte";
        }
        return "error";
    }
}
