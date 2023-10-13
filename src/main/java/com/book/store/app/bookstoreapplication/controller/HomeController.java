package com.book.store.app.bookstoreapplication.controller;

import com.book.store.app.bookstoreapplication.model.*;
import com.book.store.app.bookstoreapplication.repository.BookRepository;
import com.book.store.app.bookstoreapplication.service.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CategeoryService categeoryService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private SignupService signupService;
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    @GetMapping("/home/page/{pageNum}")
    public String home(Model model, Authentication authentication,@PathVariable(name = "pageNum") int pageNum ){
        model.addAttribute("name",authentication.getName());
        GrantedAuthority grantedAuthority = authentication.getAuthorities().stream().toList().get(0);
        List<Categeory>categeories=categeoryService.getAll();
        model.addAttribute("categeories",categeories);
        if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
            model.addAttribute("book",new Book());

            //System.out.println("Book added to Model form");
        }else {
            //pagination goes here...
            Page<Book> bookPage=bookService.findBooks(pageNum);
            List<Book> books=bookPage.getContent();
            int totalPages=bookPage.getTotalPages();
            model.addAttribute("books",books);
            model.addAttribute("totalPages",totalPages);
            model.addAttribute("currentPage",pageNum);
        }

        return "home";
    }
    @GetMapping("/view-book-details/{id}")
    public String viewBookDetails(@PathVariable(name = "id") int id,Model model,@RequestParam(name = "error",required = false) String error){
        String errorMessage="";
        if(error!=null)
            errorMessage="Book Added to Cart Successfully";
        Book book=bookRepository.getReferenceById(id);
        model.addAttribute("book",book);
        model.addAttribute("reviews",book.getReviews());
        model.addAttribute("errorMessage",errorMessage);
        model.addAttribute("grantRating",bookService.getGrantReviews(book.getReviews()));
        return "book-details";
    }
    @RequestMapping("/add-to-cart/{id}")
    public String addBookToCart(@PathVariable(name = "id") int id,Authentication authentication){
        System.out.println(authentication.getName()+" book adding to cart");
        Book book=bookRepository.getReferenceById(id);
        NewUser loggedInUser=signupService.getUserByFirstName(authentication.getName());
        if(cartService.addToCart(book,loggedInUser)!=null){
            System.out.println(authentication.getName()+" book adding to cart ended success");
            return "redirect:/view-book-details/"+id+"?error=true";
        }
        System.out.println(authentication.getName()+" book adding to cart ended failed");
        return "redirect:/view-book-details/"+id;
    }
    @GetMapping("/cart")
    public String displayCart(Authentication authentication,Model model,@RequestParam(name = "orderPlaced",required = false) String orderPlaced){
        String errorMessage="";
        if(orderPlaced!=null)
            errorMessage="Your Order Placed Successfully!! Kindly Check your E-mail for Order-Details!";
        int totalAmount=0;
        NewUser loggedInUser=signupService.getUserByFirstName(authentication.getName());
        Cart loggedInUserCart=loggedInUser.getCart();
        if(loggedInUserCart!=null){
            List<Book> books=loggedInUserCart.getAddedBooks();
            totalAmount=cartService.getGrantTotalAmount(books);
            model.addAttribute("books",books);
        }
        model.addAttribute("totalAmount",totalAmount);
        model.addAttribute("address",new Address());
        model.addAttribute("errorMessage",errorMessage);
        return "cart";
    }
    @RequestMapping("/remove-from-cart/{id}")
    public String removeFromCart(Authentication authentication,@PathVariable(name = "id") int id){
        NewUser loggedInUser=signupService.getUserByFirstName(authentication.getName());
        Book book=bookRepository.getReferenceById(id);
        Cart loggedInUserCart=loggedInUser.getCart();
        cartService.removeFromCart(book,loggedInUserCart);
        return "redirect:/cart";
    }
    @PostMapping("/fetch-books")
    public String searchBooks(@RequestParam String parameter,Model model,Authentication authentication){
        List<Book> searchList=bookService.searchBook(parameter);
        if (searchList!=null){
            model.addAttribute("books",searchList);
            model.addAttribute("totalPages",0);
            model.addAttribute("currentPage",0);
            List<Categeory>categeories=categeoryService.getAll();
            model.addAttribute("categeories",categeories);
        }
        model.addAttribute("name",authentication.getName());
        return "home";
    }
    @PostMapping("/post-review")
    public String postReview(@RequestParam String rating, @RequestParam String reviewText, @RequestParam int bookId, Authentication authentication, Model model){
        int res=bookService.postReview(rating.substring(0,1),reviewText,bookId,authentication.getName());
        return "redirect:/view-book-details/"+bookId;
    }
    @PostMapping("/place-order")
    public String placeOrder(@ModelAttribute Address address,Authentication authentication){

        try{orderService.placeOrder(address,authentication.getName());}
        catch (MessagingException ex){System.out.println(ex.getMessage());};
        return "redirect:/cart?orderPlaced=true";
    }
}
