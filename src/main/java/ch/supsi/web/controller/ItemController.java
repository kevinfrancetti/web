package ch.supsi.web.controller;


import ch.supsi.web.model.Item;
import ch.supsi.web.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value="/items", method = RequestMethod.GET)
    ResponseEntity<List<Item>> get(){
        return new ResponseEntity<>(itemRepository.findAll(), HttpStatus.OK);
    }

    private Optional<Item> getItem(int id){
        return itemRepository.findById(id);
    }

    @RequestMapping(value="/items/{id}", method = RequestMethod.GET)
    ResponseEntity<Item> get(@PathVariable int id){
        Optional<Item> itemAsked = getItem(id);
        return itemAsked.isPresent() ? new ResponseEntity<>(itemAsked.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/items", method = RequestMethod.POST)
    public ResponseEntity<Item> post(@RequestBody Item item){
        itemRepository.save(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @RequestMapping(value="/items/{id}", method = RequestMethod.PUT)
    ResponseEntity<Item> put(@PathVariable int id, @RequestBody Item item){
        Optional<Item> itemAskedToChange = getItem(id);
        if(!itemAskedToChange.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        item.setId(id);
        itemRepository.save(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @RequestMapping(value="/items/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Item> delete(@PathVariable int id){
        Optional<Item> itemAskedToDelete = getItem(id);
        if(itemAskedToDelete.isPresent()){
            Item removedItem = itemAskedToDelete.get();
            itemRepository.deleteById(id);
            return new ResponseEntity<>(removedItem, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
