package ch.supsi.web.service;

import ch.supsi.web.model.Item;
import ch.supsi.web.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;



    public List<Item> getAll(){
        return itemRepository.findAll();
    }

    public Item get(int id){
        return itemRepository.findById(id).get();
    }

    public Item persist(Item itemEntity){
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
