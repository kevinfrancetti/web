package ch.supsi.web.service;

import ch.supsi.web.model.Category;
import ch.supsi.web.model.Item;
import ch.supsi.web.model.Role;
import ch.supsi.web.model.User;
import ch.supsi.web.repository.CategotyRepository;
import ch.supsi.web.repository.ItemRepository;
import ch.supsi.web.repository.RoleRepository;
import ch.supsi.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategotyRepository categotyRepository;

    @Autowired
    private RoleRepository roleRepository;


    @PostConstruct
    public void init(){
        roleRepository.save(new Role("R1"));
        roleRepository.save(new Role("R2"));
        userRepository.save(new User("Kevin", roleRepository.findById("R1").get()));
        userRepository.save(new User("Jupiter", roleRepository.findById("R2").get()));
        categotyRepository.save(new Category("C1"));
        categotyRepository.save(new Category("C2"));
    }

    public List<Item> getAll(){
        return itemRepository.findAll();
    }

    public Item get(int id){
        return itemRepository.findById(id).get();
    }

    public Item persist(Item itemEntity){
        return itemRepository.save(itemEntity);
    }

    public void delete(int id){
            itemRepository.deleteById(id);
    }

    public boolean exist(int id){
        return itemRepository.existsById(id);
    }
}
