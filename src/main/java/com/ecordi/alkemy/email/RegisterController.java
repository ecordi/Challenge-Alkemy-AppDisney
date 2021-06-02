package com.ecordi.alkemy.email;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecordi.alkemy.entities.User;

@Controller
public class RegisterController {

    @Autowired
    private MailService mailService;

    @GetMapping("/home")
    public String index(){
        return "index";
    }

    @PostMapping("/sendMail")
    public String sendMail(@Valid@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("subject") String subject, @RequestParam("body") String body){

        String message = body +"\n\n Datos de contacto: " + "\nNombre: " + name + "\nE-mail: " + mail;
        mailService.sendMail("mail de propiedades","mail de contacto",subject,message);

        return "send_mail_view";
    }
}
