package com.example.ThreeThirty_BE.controller;

import com.example.ThreeThirty_BE.dto.login.UsermailDto;
import com.example.ThreeThirty_BE.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@AllArgsConstructor
public class MailController {
    private final MailService mailService;

    @GetMapping("/mail")
    public String dispMail(){
        return "mail.html";
    }

    @PostMapping("/mail")
    public void execMail(UsermailDto mailDto){
        mailService.mailSend(mailDto);
    }
}
