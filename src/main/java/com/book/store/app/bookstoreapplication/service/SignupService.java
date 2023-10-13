package com.book.store.app.bookstoreapplication.service;

import com.book.store.app.bookstoreapplication.model.AuthenticationAuthorizationInfo;
import com.book.store.app.bookstoreapplication.model.Cart;
import com.book.store.app.bookstoreapplication.model.NewUser;
import com.book.store.app.bookstoreapplication.model.Roles;
import com.book.store.app.bookstoreapplication.repository.AuthenticationAuthorizationInfoRepository;
import com.book.store.app.bookstoreapplication.repository.CartRepository;
import com.book.store.app.bookstoreapplication.repository.NewUserRepository;
import com.book.store.app.bookstoreapplication.repository.RolesRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private NewUserRepository newUserRepository;
    @Autowired
    private AuthenticationAuthorizationInfoRepository authenticationAuthorizationInfoRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private JavaMailSender mailSender;
    public NewUser saveUser(NewUser user,String pwd){
        AuthenticationAuthorizationInfo authenticationAuthorizationInfo=new AuthenticationAuthorizationInfo();
        authenticationAuthorizationInfo.setUserName(user.getEmailId());
        authenticationAuthorizationInfo.setPassword(pwd);
        authenticationAuthorizationInfo.setNewUser(user);
        Roles roles=rolesRepository.getReferenceById(2);
        if(roles!=null)
            authenticationAuthorizationInfo.setRoles(roles);
        Cart cart=new Cart();
        user.setIsVerified("false");
        NewUser savedUser=newUserRepository.save(user);
        cart.setUser(savedUser);
        cartRepository.save(cart);
        AuthenticationAuthorizationInfo savedInfo=authenticationAuthorizationInfoRepository.save(authenticationAuthorizationInfo);
        if(savedUser!=null && savedInfo!=null)
            return savedUser;
        return null;
    }
    public NewUser getUserByFirstName(String name){
        return newUserRepository.findByfirstName(name);
    }
    public void sendEmail(String emailId) throws MessagingException {
        String link="http://localhost:8080/email/verify/"+emailId;
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message);
        helper.setTo(emailId);
        helper.setSubject("Please click the below link to verify your mailId");
        String content = "<html><body>";
        content += "<p>Click the link below:</p>";
        content += "<a href='" + link + "'>Visit Link</a>";
        content += "</body></html>";
        helper.setText(content,true);
        mailSender.send(message);
    }
}
