package com.currantino.rfidserver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("bebraController")
@RequestMapping("/user")
public class UserController {

    @GetMapping("/form")
    public String showForm() {
        return "user-form";
    }

    @PostMapping("/echo")
    public String echoInput(@RequestParam("inputText") String inputText, Model model) {
        model.addAttribute("inputText", inputText);
        return "echo";
    }
}
