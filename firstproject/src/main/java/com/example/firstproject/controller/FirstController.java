package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username","김동현");
        return "greetings";
    }

    @GetMapping("/bye")
    public  String seeyouNext(Model model){
        model.addAttribute("username","김동현");
        return "goodBye";
    }
}
