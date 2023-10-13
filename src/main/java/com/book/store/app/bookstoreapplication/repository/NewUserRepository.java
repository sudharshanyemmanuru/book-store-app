package com.book.store.app.bookstoreapplication.repository;

import com.book.store.app.bookstoreapplication.model.NewUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewUserRepository extends JpaRepository<NewUser,Integer> {
        public NewUser findByfirstName(String firstName);
        public NewUser findByemailId(String emailId);
}
