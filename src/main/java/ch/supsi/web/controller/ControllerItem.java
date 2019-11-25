package ch.supsi.web.controller;


import ch.supsi.web.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class ControllerItem {

    List<Item> itemList = new ArrayList<>();
    static Random rnd = new Random();

    static {
        rnd.setSeed(System.currentTimeMillis());
    }

    @RequestMapping(value="/items", method = RequestMethod.GET)
    ResponseEntity<List<Item>> get(){
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    private Optional<Item> getItem(int id){
        return itemList.stream().filter(item -> item.getId() == id).findFirst();
    }


    @RequestMapping(value="/items/{id}", method = RequestMethod.GET)
    ResponseEntity<Item> get(@PathVariable int id){
        Optional<Item> itemAsked = getItem(id);
        return itemAsked.isPresent() ? new ResponseEntity<>(itemAsked.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/items", method = RequestMethod.POST)
    public ResponseEntity<Item> post(@RequestBody Item item){
        item.setId(rnd.nextInt());
        itemList.add(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @RequestMapping(value="/items/{id}", method = RequestMethod.PUT)
    ResponseEntity<Item> put(@PathVariable int id, @RequestBody Item item){
        Optional<Item> itemAskedToChange = getItem(id);
        if(!itemAskedToChange.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Item oldItem = itemAskedToChange.get();
        itemList.remove(oldItem);
        item.setId(id);
        itemList.add(item);
        return new ResponseEntity<>(oldItem, HttpStatus.OK);
    }

    @RequestMapping(value="/items/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Item> delete(@PathVariable int id){
        Optional<Item> itemAskedToDelete = getItem(id);

        if(itemAskedToDelete.isPresent()){
            Item removedItem = itemAskedToDelete.get();
            itemList.remove(removedItem);
            return new ResponseEntity<>(removedItem, HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
