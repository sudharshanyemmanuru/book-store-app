package com.book.store.app.bookstoreapplication.service;

import com.book.store.app.bookstoreapplication.model.Address;
import com.book.store.app.bookstoreapplication.model.Book;
import com.book.store.app.bookstoreapplication.model.NewUser;
import com.book.store.app.bookstoreapplication.model.Orders;
import com.book.store.app.bookstoreapplication.repository.OrdersRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private SignupService signupService;
    @Autowired
    private EmailService emailService;
    public void placeOrder(Address address,String userName) throws MessagingException {
        NewUser loggedInUser=signupService.getUserByFirstName(userName);
        address.setTotalAmount(0);
        Orders order=new Orders();
        List<Book>books=loggedInUser.getCart().getAddedBooks();
        books.forEach(book->order.getOrderedBooks().add(book));
        books.forEach(book -> book.getOrdersList().add(order));
        order.setAddress(address);
        order.setUser(loggedInUser);
        loggedInUser.getCart().getAddedBooks().removeAll(books);
        ordersRepository.save(order);
        emailService.sendAcknowldgement(order,address,loggedInUser);
    }
}
