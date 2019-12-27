package ch.supsi.web.controller;

import ch.supsi.web.model.Item;
import ch.supsi.web.model.User;
import ch.supsi.web.repository.ItemRepository;
import ch.supsi.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@RestController
public class ItemController {

    private static final Logger log = Logger.getLogger(ItemController.class.getName());
    static{
        try {
            FileHandler handler = new FileHandler(ItemController.class.getName() + ".log", true);
            handler.setFormatter(new SimpleFormatter());
            log.addHandler(handler);
            log.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Autowired
    private ItemService itemService;

    @RequestMapping(value="/items", method = RequestMethod.GET)
    ResponseEntity<List<Item>> get(){
        log.info("get method called");
        return new ResponseEntity<>(itemService.getAll(), HttpStatus.OK);
    }


    @RequestMapping(value="/items/{id}", method = RequestMethod.GET)
    ResponseEntity<Item> get(@PathVariable int id){
        return itemService.exist(id) ? new ResponseEntity<>(itemService.get(id), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/items", method = RequestMethod.POST)
    public ResponseEntity<Item> post(@RequestBody Item item){
        if(itemService.exist(item.getId()))
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        itemService.persist(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @RequestMapping(value="/items/{id}", method = RequestMethod.PUT)
    ResponseEntity<Item> put(@PathVariable int id, @RequestBody Item item){
        if(!itemService.exist(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        item.setId(id);
        itemService.persist(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @RequestMapping(value="/items/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Item> delete(@PathVariable int id){
        if(itemService.exist(id)){
            Item removedItem = itemService.get(id);
            itemService.delete(id);
            return new ResponseEntity<>(removedItem, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
