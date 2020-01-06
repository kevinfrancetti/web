package ch.supsi.web.service;


import ch.supsi.web.model.Category;
import ch.supsi.web.repository.CategotyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategotyRepository categotyRepository;


    public Category getById(String id){
        return categotyRepository.findById(id).get();
    }

    public List<Category> getAll(){
        return categotyRepository.findAll();
    }


}



