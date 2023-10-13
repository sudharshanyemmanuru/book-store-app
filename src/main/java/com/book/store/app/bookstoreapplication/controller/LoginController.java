package com.book.store.app.bookstoreapplication.controller;

import com.book.store.app.bookstoreapplication.model.NewUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(@RequestParam(name = "error",required = false) String error,
                            @RequestParam(name = "logout",required = false) String logout,
                            @RequestParam(name = "signup",required = false) String signup, Model model){
        String errorMessage="";
        if(error!=null)
            errorMessage="provide valid credintails!! or Verify your Email!!";
         if(logout!=null)
            errorMessage="You have logged out successfully!!";
        if(signup!=null)
            errorMessage="Account Created Successfully!!!.Please Check your mail and click the link that we have sent to you to verify your E-mail!!";
        model.addAttribute("errorMessage",errorMessage);
        model.addAttribute("user",new NewUser());
        return "login";
    }
}
