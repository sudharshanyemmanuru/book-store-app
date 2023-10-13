package com.book.store.app.bookstoreapplication.repository;

import com.book.store.app.bookstoreapplication.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
