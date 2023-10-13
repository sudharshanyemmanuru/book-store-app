package com.book.store.app.bookstoreapplication.service;

import com.book.store.app.bookstoreapplication.model.NewUser;
import com.book.store.app.bookstoreapplication.model.ProfilePicture;
import com.book.store.app.bookstoreapplication.repository.ProfilePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfilePictureService {
    @Autowired
    private ProfilePictureRepository profilePictureRepository;
    public ProfilePicture upload(MultipartFile profilePicture, NewUser user) throws IOException {
        if(user.getProfilePicture()!=null){
            ProfilePicture pic=user.getProfilePicture();
            pic.setImg(profilePicture.getBytes());
            ProfilePicture savedPic= profilePictureRepository.save(pic);
            return savedPic;
        }else {
            ProfilePicture profilePicture1=new ProfilePicture();
            profilePicture1.setImg(profilePicture.getBytes());
            profilePicture1.setNewUser(user);
            return profilePictureRepository.save(profilePicture1);
        }
    }
    public ProfilePicture getProfilePicById(String id){
        return profilePictureRepository.getReferenceById(id);
    }
}
