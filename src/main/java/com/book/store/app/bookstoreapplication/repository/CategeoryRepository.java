package com.book.store.app.bookstoreapplication.repository;

import com.book.store.app.bookstoreapplication.model.Categeory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategeoryRepository extends JpaRepository<Categeory,Integer> {
}
