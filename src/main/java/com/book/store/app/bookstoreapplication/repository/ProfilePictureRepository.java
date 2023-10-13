package com.book.store.app.bookstoreapplication.repository;

import com.book.store.app.bookstoreapplication.model.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePictureRepository extends JpaRepository<ProfilePicture,String> {
}
