package com.book.store.app.bookstoreapplication.service;

import com.book.store.app.bookstoreapplication.model.Categeory;
import com.book.store.app.bookstoreapplication.repository.CategeoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategeoryService {
    @Autowired
    private CategeoryRepository categeoryRepository;
    public Categeory saveCategeory(Categeory categeory){
        return categeoryRepository.save(categeory);
    }
    public List<Categeory> getAll(){return categeoryRepository.findAll();}
    public Categeory getById(int id){return categeoryRepository.getReferenceById(id);}
}
