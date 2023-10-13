package com.book.store.app.bookstoreapplication.controller;

import com.book.store.app.bookstoreapplication.model.NewUser;
import com.book.store.app.bookstoreapplication.service.SignupService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {
    @Autowired
    private SignupService signupService;
    @GetMapping("/signup")
    public String signupPage(Model model,@RequestParam(name = "error",required = false) String error){
        String errorMessage="";
        if(error!=null)
            errorMessage="Your Account created successfully!!";
        model.addAttribute("user",new NewUser());
        model.addAttribute("errorMessage",errorMessage);
        return "signup";
    }
    @PostMapping("/create-new-account")
    public String createAccount(@ModelAttribute(name = "user") NewUser user, @RequestParam(name = "pwd") String pwd){
        NewUser isSaved=null;
        try {
            signupService.sendEmail(user.getEmailId());
            isSaved=signupService.saveUser(user,pwd);
            System.out.println("Email Send to user success!!");
        }catch (MessagingException ex){
            System.out.println(ex.getMessage());
        }
        if(isSaved!=null)
            return "redirect:/login?signup=true";
        System.out.println("Account not created");
        return "redirect:/login?signup=true";
    }
}
