package com.book.store.app.bookstoreapplication.service;

import com.book.store.app.bookstoreapplication.model.Book;
import com.book.store.app.bookstoreapplication.model.Cart;
import com.book.store.app.bookstoreapplication.model.NewUser;
import com.book.store.app.bookstoreapplication.repository.BookRepository;
import com.book.store.app.bookstoreapplication.repository.CartRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;


    public Book addToCart(Book book, NewUser loggedInUser){
        Book result=null;

        if(loggedInUser.getCart()!=null){
            Cart cart=loggedInUser.getCart();
            cart.getAddedBooks().add(book);
            book.getCartList().add(cart);
            result=bookRepository.save(book);
        }else{
            Cart cart=new Cart();
            cart.setUser(loggedInUser);
            cart.setAddedBooks(Arrays.asList(book));
            result=bookRepository.save(book);
        }
        return result;
    }
    public Book removeFromCart(Book book,Cart loggedInUserCart){
        loggedInUserCart.getAddedBooks().remove(book);
        book.getCartList().remove(loggedInUserCart);
        return bookRepository.save(book);
    }
    public int getGrantTotalAmount(List<Book>books){
        int total=0;
        for(Book book:books)
            total+=book.getPrice();
        return total;
    }
}
