package com.book.store.app.bookstoreapplication.restservice;

import com.book.store.app.bookstoreapplication.model.NewUser;
import com.book.store.app.bookstoreapplication.repository.NewUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailVerifyController {
    @Autowired
    private NewUserRepository userRepository;
    @GetMapping("/verify/{emailId}")
    public String verifyEmail(@PathVariable  String emailId){
        NewUser user=userRepository.findByemailId(emailId);
        if(user!=null)
        {
            user.setIsVerified("true");
            if(userRepository.save(user)!=null)
                return "Verification done successfully!!";
        }
        return "verification failed";
    }
}
