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

        Role r1 = new Role("ROLE_1");
        Role r2 = new Role("ROLE_2");
        List<Role> roles = new ArrayList<>();
        roles.add(r1);
        roles.add(r2);

        Category c1 = new Category("GATTI");
        Category c2 = new Category("CANI");
        List<Category> categories = new ArrayList<>();
        categories.add(c1);
        categories.add(c2);

        User u1 = new User("user1", r1);
        User u2 = new User("user2", r2);
        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);

        roleRepository.saveAll(roles);
        userRepository.saveAll(users);
        categotyRepository.saveAll(categories);


        /*
        User u1 = new User("root");
        Category c1 = new Category("GATTI");
        String t1 = "Vendo gatti";
        String d1 = "Vendo gatti moldo belli";

        Item i1 = new Item().setTitle(t1).setDescription(d1).setAuthor(u1).setCategory(c1);
        itemRepository.save(i1);
        */

    }

    public List<Item> getAll(){
        return itemRepository.findAll();
    }

    public Item get(int id){
        return itemRepository.findById(id).get();
    }

    public Item persist(Item itemEntity){
        //User u = itemEntity.getAuthor();//TODO change this
        //userRepository.save(u);//TODO change this
        return itemRepository.save(itemEntity);
    }

    public boolean delete(int id){
        if (exist(id)) {
            itemRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public boolean exist(int id){
        return itemRepository.existsById(id);
    }
}
