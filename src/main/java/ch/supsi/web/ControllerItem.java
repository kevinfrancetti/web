package ch.supsi.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerItem {

    List<Item> itemList = new ArrayList<>();

    @RequestMapping(value="/items", method = RequestMethod.GET)
    ResponseEntity<List<Item>> get(){
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @RequestMapping(value="/items/{id}", method = RequestMethod.GET)
    ResponseEntity<Item> getItem(@PathVariable int id){
        return new ResponseEntity<>(itemList.get(id), HttpStatus.OK);
    }

    @RequestMapping(value="/items", method = RequestMethod.POST)
    public ResponseEntity<Item> post(@RequestBody Item item){
        itemList.add(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @RequestMapping(value="/items", method = RequestMethod.PUT)
    String test3(){
        return "Hello worldT";
    }

    @RequestMapping(value="/items/{id}", method = RequestMethod.DELETE)
    String delete(@PathVariable int id){
        itemList.remove(id);
        return "Hello world4";
    }
}
