package com.book.store.app.bookstoreapplication.restservice;

import com.book.store.app.bookstoreapplication.model.Book;
import com.book.store.app.bookstoreapplication.model.ProfilePicture;
import com.book.store.app.bookstoreapplication.repository.BookRepository;
import com.book.store.app.bookstoreapplication.service.ProfilePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfilePicRestController {
    @Autowired
    private ProfilePictureService profilePictureService;
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/show-profile-pic/{id}")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable String id){
        ProfilePicture picture=profilePictureService.getProfilePicById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(picture.getImg());
    }
    @GetMapping("/show-book-cover-img/{id}")
    public ResponseEntity<byte[]> getBookCoverImage(@PathVariable(name = "id") int id){
        Book book=bookRepository.getReferenceById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(book.getBookCoverImage());
    }
}
