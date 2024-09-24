package com.example.calculator.controllers;


import com.example.calculator.dao.CalculacteDAO;
import com.example.calculator.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller()
public class MainController {
    private CalculacteDAO calculacteDAO;

    @Autowired
    public MainController(CalculacteDAO calculacteDAO) {
        this.calculacteDAO = calculacteDAO;
    }

    @GetMapping("/calculacte")
    public String calculacte(@ModelAttribute("payment") Payment payment, Model model) {

        String answer = calculacteDAO.calculation(payment, model);

        if (answer == "success") {
            return "calculacte";
        }
        return "error";
    }
}
