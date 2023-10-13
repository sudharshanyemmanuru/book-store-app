package com.book.store.app.bookstoreapplication.controller;

import com.book.store.app.bookstoreapplication.model.Author;
import com.book.store.app.bookstoreapplication.model.Book;
import com.book.store.app.bookstoreapplication.model.Categeory;
import com.book.store.app.bookstoreapplication.repository.BookRepository;
import com.book.store.app.bookstoreapplication.service.CategeoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategeoryService categeoryService;
    @Autowired
    private BookRepository bookRepository;
    @PostMapping("/add-new-categeory")
    public String addCategeory(@RequestParam(name = "cat") String cat){
        Categeory categeory=new Categeory();
        categeory.setCategeoryName(cat);
        Categeory addedCategeory=categeoryService.saveCategeory(categeory);
        if(addedCategeory!=null){
            return "redirect:/home/page/1";
        }
        return "redirect:/home/page/1";
    }
    @PostMapping("/add-new-book")
    public String addBook(@ModelAttribute(name="book")Book book,
                          @RequestParam(name = "bookCoverImg") MultipartFile bookCoverImg,
                          @RequestParam(name = "authName") String authName,
                          @RequestParam(name = "authSummary") String authSummary,
                          @RequestParam(name = "categeoryId") int categeoryId) throws IOException {
        Author author=new Author();
        author.setAuthorName(authName);author.setAboutAuthor(authSummary);
        book.setAuthor(author);
        book.setBookCoverImage(bookCoverImg.getBytes());
        Categeory categeory=categeoryService.getById(categeoryId);
        book.setCategeory(categeory);
        if(bookRepository.save(book)!=null)
            return "redirect:/home/page/1";
        return "redirect:/home/page/1";
    }
    @GetMapping("/view-books/{id}")
    public String viewBooks(@PathVariable(name = "id") int id, Model model){
        Categeory categeory=categeoryService.getById(id);
        model.addAttribute("books",categeory.getBooks());
        return "books";
    }

}
