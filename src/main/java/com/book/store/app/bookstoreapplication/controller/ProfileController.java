package com.book.store.app.bookstoreapplication.controller;

import com.book.store.app.bookstoreapplication.model.NewUser;
import com.book.store.app.bookstoreapplication.model.ProfilePicture;
import com.book.store.app.bookstoreapplication.repository.NewUserRepository;
import com.book.store.app.bookstoreapplication.service.ProfilePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Controller
public class ProfileController {
    @Autowired
    private NewUserRepository newUserRepository;
    @Autowired
    private ProfilePictureService profilePictureService;
    @GetMapping("/profile")
    public String getProfile(Authentication authentication, Model model){
        String isProfilePresent="";
        NewUser user=newUserRepository.findByfirstName(authentication.getName());
        if(user.getProfilePicture()!=null){
            isProfilePresent="profile present";
            ProfilePicture pic=user.getProfilePicture();
            UriComponentsBuilder uri=UriComponentsBuilder.fromUriString("http://localhost:8080/show-profile-pic")
                    .pathSegment(pic.getProfilePicId());
            model.addAttribute( "profileUri",uri.toUriString());
        }else
            System.out.println("Not present");
        model.addAttribute("isProfilePresent",isProfilePresent);
        model.addAttribute("user",user);
        return "profile";
    }
    @PostMapping("/upload-profile-pic")
    public String uploadProfilePicture(@RequestParam MultipartFile profilePicture,Authentication authentication) throws IOException {
        NewUser user=newUserRepository.findByfirstName(authentication.getName());
        if(user!=null){
            ProfilePicture picture=profilePictureService.upload(profilePicture,user);
            if(picture!=null && picture.getProfilePicId()!=null){
                return "redirect:/profile";
            }
        }
        return "redirect:/profile";
    }
}
