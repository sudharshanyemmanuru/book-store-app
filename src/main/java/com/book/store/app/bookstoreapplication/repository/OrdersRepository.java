package com.book.store.app.bookstoreapplication.repository;

import com.book.store.app.bookstoreapplication.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,String> {
}
