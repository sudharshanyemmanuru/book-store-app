package com.book.store.app.bookstoreapplication.repository;

import com.book.store.app.bookstoreapplication.model.AuthenticationAuthorizationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationAuthorizationInfoRepository extends JpaRepository<AuthenticationAuthorizationInfo,Integer> {
    public AuthenticationAuthorizationInfo findByuserName(String emailId);
}
