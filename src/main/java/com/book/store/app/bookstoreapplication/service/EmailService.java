package com.book.store.app.bookstoreapplication.service;

import com.book.store.app.bookstoreapplication.model.Address;
import com.book.store.app.bookstoreapplication.model.Book;
import com.book.store.app.bookstoreapplication.model.NewUser;
import com.book.store.app.bookstoreapplication.model.Orders;
import com.book.store.app.bookstoreapplication.repository.NewUserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendAcknowldgement(Orders order, Address address, NewUser user) throws MessagingException {
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message);
        helper.setTo(user.getEmailId());
        helper.setSubject("Your Order Placed Succefully!!");
        String content="Order Id : "+order.getOrderId()+"\n";
        int i=1;
        for(Book book:order.getOrderedBooks()){
            content+=i+book.getBookName()+"\n";
            i++;
        }
        content+="Address :"+address.toString()+"\n";
        content+="Total Amount to be Paid : "+address.getTotalAmount();
        helper.setText(content);
        mailSender.send(message);
        System.out.println("Mail Order Sent Successfully!!");
    }
}
