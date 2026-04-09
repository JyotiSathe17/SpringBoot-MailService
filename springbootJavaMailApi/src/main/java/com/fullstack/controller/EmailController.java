package com.fullstack.controller;

import com.fullstack.model.EmailModel;
import com.fullstack.service.EmailService;
import com.fullstack.service.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final IEmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody EmailModel emailModel){
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>("email send successfully", HttpStatus.OK);
    }
}
